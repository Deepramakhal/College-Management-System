// Store the popup state when the button is clicked
function storePopupState(popupId) {
	location.reload();
    localStorage.setItem('activePopup', popupId);
}

document.addEventListener('DOMContentLoaded', () => {
    const activePopup = localStorage.getItem('activePopup');
    if (activePopup) {
        openPopup(activePopup);
        localStorage.removeItem('activePopup'); // Remove from localStorage after opening
    }
});

function openPopup(popupId) {
    const popup = document.getElementById(popupId);
    if (popup) {
        popup.style.display = 'flex';
    }
}

// Close the popup by setting display to 'none'
function closePopup(popupId) {
	const popup = document.getElementById(""+popupId);
	if (popup) {
		popup.style.display = 'none';
	}
}

function getParameterValue(paramName) {
    const urlParams = new URLSearchParams(window.location.search);
    return urlParams.get(paramName);
}

function openPdfs(files) {
            files.forEach(url => {
                window.open("../"+url, '_blank');
            });
}

const sidebar = document.getElementById("sidebar");
const toggleSidebar = document.getElementById("toggleSidebar");
const openSidebar = document.getElementById("openSidebar");

toggleSidebar.addEventListener("click", () => {
    sidebar.classList.remove("active"); // Close sidebar
    openSidebar.style.display = "block"; // Show open button
});

openSidebar.addEventListener("click", () => {
    sidebar.classList.add("active"); // Open sidebar
    openSidebar.style.display = "none"; // Hide open button
});

function removeStudent() {
    document.getElementsByClassName("confirmation")[0].style.display = "flex";
}

function closeConfirmation() {
    document.getElementsByClassName("confirmation")[0].style.display = "none";
}

function showFileName() {
        const fileInput = document.getElementById('file-upload');
        const fileLabel = document.querySelector('.custom-file-label');
        fileLabel.textContent = fileInput.files[0] ? fileInput.files[0].name : "Choose a file";
}
function deRegister(id){
	document.getElementById("confirmation").style.display = "flex";
	document.getElementById("dynamicData").value = id;
}
function closeDeregister(e){
	e.preventDefault();
	document.getElementById("confirmation").style.display = "none";
	document.getElementById("dynamicData").value = "";
}