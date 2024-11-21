// Profile Popup
const profileButton = document.getElementById("profileButton");
const profilePopup = document.getElementById("profilePopup");
const closeProfilePopup = document.getElementById("closeProfilePopup");

// Show profile popup
profileButton.addEventListener("click", () => {
    profilePopup.style.display = "flex";
});

// Close profile popup
closeProfilePopup.addEventListener("click", () => {
    profilePopup.style.display = "none";
});

// Close popup if clicked outside content
profilePopup.addEventListener("click", (e) => {
    if (e.target === profilePopup) {
        profilePopup.style.display = "none";
    }
});
