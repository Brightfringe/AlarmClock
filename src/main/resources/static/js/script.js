let alarmAudio = new Audio('/audio/alarm.mp3');
alarmAudio.preload = "auto";
let alarmInterval = null;

// Live Clock
function updateClock() {
    const clock = document.getElementById("clock");
    const now = new Date();
    const timeString = now.toLocaleTimeString("en-GB", { hour12: false });
    clock.textContent = timeString;

    // Only check alarms on exact minute
    const currentTime = timeString.slice(0, 5); // "HH:mm"

    document.querySelectorAll("tbody tr").forEach(row => {
        const alarmTime = row.children[0].innerText; // time from table
        const active = row.children[2].innerText === "On";

        if (active && alarmTime === currentTime) {
            ringAlarm(alarmTime);
        }
    });
}
setInterval(updateClock, 1000);

// 🔔 Ring Alarm
function ringAlarm(label) {
    if (alarmInterval) return; // prevent multiple alarms

    const popup = document.getElementById("alarmPopup");
    popup.style.display = "flex";

    alarmAudio.loop = true;

    alarmAudio.play().then(() => {
        console.log("Alarm ringing:", label);
    }).catch(err => {
        console.log("Autoplay blocked. Click anywhere once to unlock sound.");
    });

    alarmInterval = setInterval(() => {
        console.log("Alarm still ringing:", label);
    }, 1000);
}

// ✋ Stop Alarm
function stopAlarm() {
    if (alarmInterval) {
        clearInterval(alarmInterval);
        alarmInterval = null;
    }
    alarmAudio.pause();
    alarmAudio.currentTime = 0;
    document.getElementById("alarmPopup").style.display = "none";
}

// Stop button
document.getElementById("stopAlarm").addEventListener("click", stopAlarm);

// Stop with Enter
document.addEventListener("keydown", function(event) {
    if (event.key === "Enter") {
        stopAlarm();
    }
});

// 🔑 Unlock audio autoplay after first click
document.addEventListener("click", () => {
    alarmAudio.play().then(() => {
        alarmAudio.pause();
        alarmAudio.currentTime = 0;
        console.log("✅ Audio unlocked for autoplay");
    }).catch(err => console.log("Still blocked:", err));
}, { once: true });













