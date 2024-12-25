document.querySelectorAll('.carousel').forEach(carousel => {
    const images = carousel.querySelector('.carousel-images');
    const prevButton = carousel.querySelector('.prev');
    const nextButton = carousel.querySelector('.next');
    let currentIndex = 0;

    const updateCarousel = () => {
        const imageWidth = images.children[0].offsetWidth;
        images.style.transform = `translateX(-${currentIndex * imageWidth}px)`;
    };

    prevButton.addEventListener('click', () => {
        currentIndex = (currentIndex > 0) ? currentIndex - 1 : images.children.length - 1;
        updateCarousel();
    });

    nextButton.addEventListener('click', () => {
        currentIndex = (currentIndex < images.children.length - 1) ? currentIndex + 1 : 0;
        updateCarousel();
    });
});
