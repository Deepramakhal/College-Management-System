function openPopup(popupId) {
    const popup = document.getElementById(popupId);
    document.getElementById('role').value = popupId;
    document.querySelector('.overlay').style.display = 'flex';
    popup.style.display = 'flex';
}

function closePopup(popupId) {
    const popup = document.getElementById(popupId);

    document.querySelector('.overlay').style.display = 'none';
    popup.style.display = 'none';
}

function openHelpdeskPopup(Helpdesk) {
    const popup = document.getElementById(Helpdesk);
    if (popup) {
        popup.style.display = "flex";
    }
}

// Function to close the popup
function closeHelpdeskPopup(Helpdesk) {
    const popup = document.getElementById(Helpdesk);
    if (popup) {
        popup.style.display = "none";
    }
}


const carousel = document.getElementById('carousel');

function scrollCarousel(direction) {
  const scrollAmount = 300; 
  if (direction === 'left') {
    carousel.scrollBy({ left: -scrollAmount, behavior: 'smooth' });
  } else if (direction === 'right') {
    carousel.scrollBy({ left: scrollAmount, behavior: 'smooth' });
  }
}