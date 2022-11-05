const patterns = {
    password: /^(?=.*[A-Za-z])(?=.*\d)[A-Za-z\d]{8,}$/i,
    first_name: /^[a-z ,.'-]+$/i,
    last_name: /^[a-z ,.'-]+$/i,
    email: /^([a-z\d\.-]+)@([a-z\d-]+)\.([a-z]{2,8})(\.[a-z]{2,8})?$/,
    money: /^([0-9]+(?:[\.][0-9]*)?|\.[0-9]+)$/,
    phone: /\+?\(?\d{2,4}\)?[\d\s-]{3,}/
};

function validateSignIn() {
    document.querySelector('#email').addEventListener('change', validateEmail);
    const form = document.querySelector('#signInForm');
    form.addEventListener(
        'submit',
        function (event) {
            let isValid = true;
            isValid = form.checkValidity() && isValid;
            isValid = validateEmail() && isValid;
            if (
                !isValid
            ) {
                event.preventDefault();
                event.stopPropagation();
            } else {
                form.classList.add('was-validated');
            }
        },
        false
    );

}

function validateSignUp() {
    document.querySelector('#first_name').addEventListener('change', validateFirstName);
    document.querySelector('#last_name').addEventListener('change', validateLastName);
    document.querySelector('#email').addEventListener('change', validateEmail);
    document.querySelector('#phone').addEventListener('change', validatePhone);
    document.querySelector('#password').addEventListener('change', validatePassword);
    document.querySelector('#confirm_password').addEventListener('change', validatePasswordConfirm);
    const form = document.querySelector('#signUpForm');
    form.addEventListener(
        'submit',
        function (event) {
            let isValid = true;
            isValid = form.checkValidity() && isValid;
            isValid = validateEmail() && isValid;
            isValid = validateFirstName() && isValid;
            isValid = validateLastName() && isValid;
            isValid = validatePhone() && isValid;
            isValid = validatePassword() && isValid;
            isValid = validatePasswordConfirm() && isValid;
            isValid = validateRecaptcha() && isValid;
            if (
                !isValid
            ) {
                event.preventDefault();
                event.stopPropagation();
            } else {
                form.classList.add('was-validated');
            }
        },
        false
    );
}

function validateProfile() {
    document.querySelector('#first_name').addEventListener('change', validateFirstName);
    document.querySelector('#last_name').addEventListener('change', validateLastName);
    document.querySelector('#email').addEventListener('change', validateEmail);
    document.querySelector('#phone').addEventListener('change', validatePhone);
    const form = document.querySelector('#profileForm');
    form.addEventListener(
        'submit',
        function (event) {
            let isValid = true;
            isValid = form.checkValidity() && isValid;
            isValid = validateEmail() && isValid;
            isValid = validateFirstName() && isValid;
            isValid = validateLastName() && isValid;
            isValid = validatePhone() && isValid;
            if (
                !isValid
            ) {
                event.preventDefault();
                event.stopPropagation();
            } else {
                form.classList.add('was-validated');
            }
        },
        false
    );
}

function validateProfilePassword() {
    document.querySelector('#new_password').addEventListener('change', validateNewPassword);
    document.querySelector('#confirm_new_password').addEventListener('change', validateNewPasswordConfirm);
    const form = document.querySelector('#profilePasswordForm');
    form.addEventListener(
        'submit',
        function (event) {
            let isValid = true;
            isValid = form.checkValidity() && isValid;
            isValid = validateNewPassword() && isValid;
            isValid = validateNewPasswordConfirm() && isValid;
            if (
                !isValid
            ) {
                event.preventDefault();
                event.stopPropagation();
            } else {
                form.classList.add('was-validated');
            }
        },
        false
    );

}

function validateBalance() {
    document.querySelector('#money').addEventListener('change', validateMoney);
    const form = document.querySelector('#balanceForm');
    form.addEventListener(
        'submit',
        function (event) {
            let isValid = true;
            isValid = form.checkValidity() && isValid;
            isValid = validateMoney() && isValid;
            if (
                !isValid
            ) {
                event.preventDefault();
                event.stopPropagation();
            } else {
                form.classList.add('was-validated');
            }
        },
        false
    );

}

function validateRecaptcha() {
    let response = grecaptcha.enterprise.getResponse();
    if (response) {
        document.getElementById('recaptcha-alert').setAttribute('hidden', '');
        return true;
    } else {
        document.getElementById('recaptcha-alert').removeAttribute('hidden');
        return false;
    }
}

function validateMoney(e) {
    const money = document.querySelector('#money');
    return validate(money, patterns.money);
}

function validateEmail(e) {
    const email = document.querySelector('#email');
    return validate(email, patterns.email);
}

function validateFirstName(e) {
    const firstName = document.querySelector('#first_name');
    return validate(firstName, patterns.first_name);
}

function validateLastName(e) {
    const lastName = document.querySelector('#last_name');
    return validate(lastName, patterns.last_name);
}

function validatePhone(e) {
    const phone = document.querySelector('#phone');
    return validate(phone, patterns.phone);
}

function validateNewPassword(e) {
    const password = document.querySelector('#new_password');
    const cPassword = document.querySelector('#confirm_new_password');
    return validate(password, patterns.password) && confirmPassword(password, cPassword);
}

function validateNewPasswordConfirm(e) {
    const password = document.querySelector('#new_password');
    const cPassword = document.querySelector('#confirm_new_password');
    return confirmPassword(password, cPassword);
}

function validatePassword(e) {
    const password = document.querySelector('#password');
    const cPassword = document.querySelector('#confirm_password');
    return validate(password, patterns.password) && confirmPassword(password, cPassword);
}

function validatePasswordConfirm(e) {
    const password = document.querySelector('#password');
    const cPassword = document.querySelector('#confirm_password');
    return confirmPassword(password, cPassword);
}

function confirmPassword(password, passwordConfirm) {
    if (password.value === passwordConfirm.value && password.value !== '') {
        passwordConfirm.classList.remove('is-invalid');
        passwordConfirm.classList.add('is-valid');
        return true;
    } else {
        passwordConfirm.classList.add('is-invalid');
        passwordConfirm.classList.remove('is-valid');
        return false;
    }
}

function validate(field, regex) {
    if (regex.test(field.value)) {
        field.classList.remove('is-invalid');
        field.classList.add('is-valid');
        return true;
    } else {
        field.classList.add('is-invalid');
        field.classList.remove('is-valid');
        return false;
    }
}