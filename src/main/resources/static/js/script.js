const alarmAudio = document.getElementById('alarmSound');
const popup = document.getElementById('alarmPopup');
const triggeredAlarms = new Set();


function showAlarmPopup() { popup.style.display = 'block'; }
function hideAlarmPopup() {
    popup.style.display = 'none';
    alarmAudio.pause();
    alarmAudio.currentTime = 0;
}


document.addEventListener('keydown', function(event) {
    if (event.key === "Enter") hideAlarmPopup();
});


function updateClock() {
    const now = new Date();
    const hh = now.getHours().toString().padStart(2,'0');
    const mm = now.getMinutes().toString().padStart(2,'0');
    const ss = now.getSeconds().toString().padStart(2,'0');
    document.getElementById('liveClock').innerText = `${hh}:${mm}:${ss}`;
}
setInterval(updateClock, 1000);
updateClock();


function checkAlarms() {
    const now = new Date();
    const currentTime = now.getHours().toString().padStart(2,'0') + ":" + now.getMinutes().toString().padStart(2,'0');

    document.querySelectorAll("tbody tr").forEach(row => {
        const time = row.getAttribute('data-time');
        const isActive = row.getAttribute('data-active') === 'true';

        if (isActive && time === currentTime && !triggeredAlarms.has(time)) {
            triggeredAlarms.add(time);
            alarmAudio.play().catch(err => console.log(err));
            showAlarmPopup();
        }

        if (time !== currentTime && triggeredAlarms.has(time)) {
            triggeredAlarms.delete(time);
        }
    });
}
setInterval(checkAlarms, 1000);











