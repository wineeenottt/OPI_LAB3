function showClientError(message) {
    const errorDiv = document.getElementById('clientErrors');
    if (errorDiv) {
        errorDiv.textContent = message;
        errorDiv.style.display = 'block';
    }
}

function hideClientErrors() {
    const errorDiv = document.getElementById('clientErrors');
    if (errorDiv) {
        errorDiv.style.display = 'none';
        errorDiv.textContent = '';
    }
}

function handleSvgClick(event) {
    const svg = event.currentTarget;
    const R = parseFloat(svg.getAttribute('data-r'));

    if (!R || isNaN(R)) {
        showClientError('Пожалуйста, выберите R перед кликом по графику');
        return;
    }

    hideClientErrors();

    const pt = svg.createSVGPoint();
    pt.x = event.clientX;
    pt.y = event.clientY;
    const svgPoint = pt.matrixTransform(svg.getScreenCTM().inverse());

    const center = 300;
    const x = ((svgPoint.x - center) * R / 225);
    const y = ((center - svgPoint.y) * R / 225);

    document.getElementById('controlForm:clickX').value = x;
    document.getElementById('controlForm:clickY').value = y;

   document.getElementById('controlForm:hiddenClickButton').click();
}

function updatePointsColor(rVal) {
    const CENTER = 300;
    const SCALE = 225;

    hideClientErrors();

    document.querySelectorAll("svg#image circle").forEach(point => {
        const x = parseFloat(point.dataset.x);
        const y = parseFloat(point.dataset.y);

        const cx = CENTER + (x / rVal) * SCALE;
        const cy = CENTER - (y / rVal) * SCALE;

        if (cx < 0 || cx > 600 || cy < 0 || cy > 600) {
            showClientError("Невозможно определить координаты точки/точек. Она/они выходят за пределы графика при текущем R");
        }

        point.setAttribute("cx", cx);
        point.setAttribute("cy", cy);

        const isHit = checkArea(x, y, rVal);
        point.setAttribute("fill", isHit ? "lime" : "red");
    });
}

function checkArea(x, y, r) {
    return areaRectangle(x, y, r) || areaCircle(x, y, r) || areaTriangle(x, y, r);
}

function areaRectangle(x, y, r) {
    return x >= 0 && x <= r && y <= 0 && y >= -r/2;
}

function areaCircle(x, y, r) {
    const halfR = r / 2;
    return x >= 0 && y >= 0 && (x*x + y*y <= halfR*halfR);
}

function areaTriangle(x, y, r) {
    return x <= 0 && y <= 0 && (x + 2*y + r >= 0);
}

function clearYError() {
    hideClientErrors();
}

function validateX(x) {
    if (x === null || isNaN(x)) {
        showClientError("Выберите X");
        return false;
    }
    if (x < -5 || x > 3) {
        showClientError("X должно быть в диапазоне [-5; 3]");
        return false;
    }
    return true;
}

function validateR(r) {
    if (r === null || isNaN(r)) {
        showClientError("Выберите R");
        return false;
    }
    if (r < 1 || r > 5) {
        showClientError("R должно быть в диапазоне [1; 5]");
        return false;
    }
    return true;
}

function validateY() {
    const yInput = document.getElementById('controlForm:yInput');
    if (!yInput) return false;

    const yStr = yInput.value.trim().replace(',', '.');

    if (!/^-?\d+(\.\d+)?$/.test(yStr)) {
        showClientError("Y должен быть числом");
        return false;
    }

    const yVal = new Decimal(yStr);

    if (yVal.lessThan(-5) || yVal.greaterThan(5)) {
        showClientError("Y должен быть в диапазоне [-5; 5]");
        return false;
    }

    hideClientErrors();
    return true;
}

function validateForm() {
    const selectedXButton = document.querySelector('.x-buttons-container .selected-x');
    const x = selectedXButton ? parseFloat(selectedXButton.textContent) : null;
    if (!validateX(x)) return false;

    const svg = document.getElementById('image');
    const r = parseFloat(svg.getAttribute('data-r'));
    if (!validateR(r)) return false;

    if (!validateY()) return false;

    return true;
}

function formatLocalTime() {
    document.querySelectorAll('.local-time').forEach(el => {
        const tsStr = el.getAttribute('data-timestamp');
        if (tsStr && !el.textContent.trim()) {
            const timestamp = Number(tsStr);
            if (!isNaN(timestamp)) {
                const date = new Date(timestamp);
                el.textContent = date.toLocaleTimeString([], {
                    hour: '2-digit',
                    minute: '2-digit',
                    second: '2-digit',
                    hour12: false
                });
            }
        }
    });
}
document.addEventListener('DOMContentLoaded', formatLocalTime);




