const navBtn = document.querySelector('.header__collapse--btn');
const nav = document.querySelector('.nav');
const navWrapper = document.querySelector('.navWrapper');
const navClose = document.querySelector('.nav__close');

// Nav
const showOrder = document.querySelector('.nav__showOrders');
const showProducts = document.querySelector('.nav__showProducts');
const navOrdersList = document.querySelector('.nav__orders--items');
const navProductsList = document.querySelector('.nav__products--items');

// Start nav Toggle
navBtn.addEventListener('click', () => {
    console.log(`object`);
    nav.classList.toggle('show');
    // navWrapper.classList.toggle('show-item');
});

navClose.addEventListener('click', (e) => {
    console.log(e.target.parentElement);
    nav.classList.remove('show');
    // navWrapper.classList.remove('show-item');
});

showOrder.addEventListener('click', () => {
    navOrdersList.classList.toggle('show');
});
showProducts.addEventListener('click', () => {
    navProductsList.classList.toggle('show');
});

// End nav Toggle

// Start Toggle Header profile
const toggleProfileNav = document.querySelector('.header__profile');
const showProfileNav = document.querySelector('.header__profile__name--nav');
toggleProfileNav.addEventListener('click', () => {
    showProfileNav.classList.toggle('show');
});
// End Toggle Header profile

// Start Toggle orders filter
const showOrderFilter = document.querySelector(
    '.orders__table--filter--listWrapper'
);
const toggleShowOrderFilter = document.querySelector(
    '.orders__table--filter--collapseBtn'
);
toggleShowOrderFilter.addEventListener('click', () => {
    showOrderFilter.classList.toggle('show');
    console.log(`filter orders`);
});
// End Toggle orders filter

// Start Toggle abandoned orders filter
const showAbandonedOrderFilter = document.querySelector(
    '.abandoned__table--filter--listWrapper'
);
const toggleShowAbandonedOrderFilter = document.querySelector(
    '.abandoned__table--filter--collapseBtn'
);
toggleShowAbandonedOrderFilter.addEventListener('click', () => {
    console.log(`filter abandoned orders`);
    showAbandonedOrderFilter.classList.toggle('show');
});
// End Toggle abandoned orders filter



