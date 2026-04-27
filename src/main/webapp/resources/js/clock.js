let hr, mn, sc;

function startAnalogClock(initialServerTimestamp) {
    hr = document.querySelector('#hr');
    mn = document.querySelector('#mn');
    sc = document.querySelector('#sc');

    updateFromTimestamp(initialServerTimestamp);
}


function updateFromTimestamp(ts) {
    const localDate = new Date(ts);

    const h = localDate.getHours();
    const m = localDate.getMinutes();
    const s = localDate.getSeconds();

    updateClockVisual(h, m, s);
    updateTextTime(localDate);
}

function updateClockVisual(h, m, s) {
    const deg = 6;

    const hh = h * 30;
    const mm = m * deg;
    const ss = s * deg;

    hr.style.transform = `rotateZ(${hh + mm / 12}deg)`;
    mn.style.transform = `rotateZ(${mm}deg)`;
    sc.style.transform = `rotateZ(${ss}deg)`;
}

function updateTextTime(dateObj) {
    const timeElem = document.getElementById("currentTimeText");
    const dateElem = document.getElementById("currentDateText");

    if (timeElem)
        timeElem.textContent = dateObj.toLocaleTimeString();

    if (dateElem)
        dateElem.textContent = dateObj.toLocaleDateString();
}

function updateClockFromServer() {
    const tsElem = document.getElementById("serverTimestampText");

    if (tsElem) {
        const ts = Number(tsElem.textContent);
        updateFromTimestamp(ts);
    }
}
