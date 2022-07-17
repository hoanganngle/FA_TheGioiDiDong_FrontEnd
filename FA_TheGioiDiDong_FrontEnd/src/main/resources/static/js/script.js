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

// Start Add/Edit tracking button - Single order page
const showAddEditTrackingModal = document.querySelector(
    '.singleOrder__main--modal--addEditTracking'
);
const toggleShowAddEditTrackingModal = document.querySelector(
    '.singleOrder__main--modal--addEditTrackingBtn'
);
const closeShowAddEditTrackingModal = document.querySelectorAll(
    '.singleOrder__main--modal--addEditTracking--closeBtn'
);

toggleShowAddEditTrackingModal.addEventListener('click', () => {
    showAddEditTrackingModal.classList.toggle('show');
});

closeShowAddEditTrackingModal.forEach((btn) => {
    btn.addEventListener('click', () => {
        showAddEditTrackingModal.classList.toggle('show');
    });
});

// End Add/Edit tracking button - Single order page

window.addEventListener('DOMContentLoaded', () => {
    for (const i of Array(300)) {
        const p = document.createElement('p')
        p.innerText = 'Test'
        document.querySelector('body').appendChild(p)
    }
})

window.addEventListener('scroll', event => {
    console.log(`It's scrolling! ${window.scrollY} ${window.scrollX}`)
})

let cached = null
window.addEventListener('scroll', event => {
    if (!cached) {
        setTimeout(() => {
            console.warn(`It's scrolling THROTTLED! ${window.scrollY} ${window.scrollX}`)

            cached = null
        }, 1000)
    }
    cached = event
})