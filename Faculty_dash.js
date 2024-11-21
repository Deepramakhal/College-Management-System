// Wait until the DOM is fully loaded
document.addEventListener("DOMContentLoaded", () => {
    // Select the popup, open button, and close button
    const popup = document.getElementById("popup");
    const popupBtn = document.querySelector(".popupBtn");
    const closePopup = document.getElementById("closePopup");

    // Show the popup when the button is clicked
    popupBtn.addEventListener("click", () => {
        popup.style.display = "flex"; // Make the popup visible
    });

    // Hide the popup when the close button is clicked
    closePopup.addEventListener("click", () => {
        popup.style.display = "none"; // Hide the popup
    });

    // Optionally hide the popup if clicking outside the popup content
    popup.addEventListener("click", (e) => {
        if (e.target === popup) {
            popup.style.display = "none"; // Hide the popup
        }
    });
});


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

