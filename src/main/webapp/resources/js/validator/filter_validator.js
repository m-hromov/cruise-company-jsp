let date = new Date();
let day = date.getDate();
let month = date.getMonth()+1;
if (day < 10) {
    day = '0' + day;
}
if (month < 10) {
    month = '0' + month;
}
let minYear = date.getUTCFullYear() - 5;
let maxYear = date.getUTCFullYear() + 5
let minDate = minYear + "-" + month + "-" + day;
let maxDate = maxYear + "-" + month + "-" + day;

document.getElementById("dateFrom").setAttribute('min', minDate)
document.getElementById("dateTo").setAttribute('min', minDate)
document.getElementById("dateFrom").setAttribute('max', maxDate)
document.getElementById("dateTo").setAttribute('max', maxDate)

function setMinDateForDateTo() {
    let minDateTo = document.getElementById("dateFrom").value;
    document.getElementById("dateTo").setAttribute('min', minDateTo);
}

function setMaxDateForDateFrom() {
    let maxDateFrom = document.getElementById("dateTo").value;
    document.getElementById("dateFrom").removeAttribute('max');
    document.getElementById("dateFrom").setAttribute('max', maxDateFrom);
}