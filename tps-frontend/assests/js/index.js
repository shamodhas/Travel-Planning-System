$(document).ready(function (event) {
    init();
    $('#btn-sign-out').hide();
    $('#header_img').hide();
    $('#btn-open-login').show();
    guestRoleViewSet();
    const showNavbar = (toggleId, navId, bodyId, headerId) => {
        const toggle = document.getElementById(toggleId),
            nav = document.getElementById(navId),
            bodypd = document.getElementById(bodyId),
            headerpd = document.getElementById(headerId)
        if (toggle && nav && bodypd && headerpd) {
            toggle.addEventListener('click', () => {
                nav.classList.toggle('show')
                toggle.classList.toggle('bx-x')
                bodypd.classList.toggle('body-pd')
                headerpd.classList.toggle('body-pd')
            })
        }
    }
    showNavbar('header-toggle', 'nav-bar', 'body-pd', 'header')
    const linkColor = document.querySelectorAll('.nav_link')

    function colorLink() {
        if (linkColor) {
            linkColor.forEach(l => l.classList.remove('active'))
            this.classList.add('active')
        }
    }
    linkColor.forEach(l => l.addEventListener('click', colorLink))
});

const init = () => {
    $('#login-section').hide()
    navigate('dashboard');
}

$('.nav .nav_list .nav_link').click(event => {
    event.preventDefault(); // Prevent the link from navigating
    var text = $(event.currentTarget).find('.nav_name').text();
    $('#header-text').text(text);
    navigate(text);
})

const navigate = (text) => {
    $('.body-container').hide();
    $('#' + text.toLowerCase() + '-section').show();
}

$('#btn-go-customer-reg').click(event => {
    event.preventDefault();
    $('#login-form').css('display', 'none');
    $('#register-form').css('display', 'block');
})
$('#btn-go-login').click(event => {
    event.preventDefault();
    $('#register-form').css('display', 'none');
    $('#login-form').css('display', 'block');
    $('#txtCustomerName').val('');
    $('#txtCustomerEmail').val('');
    $('#txtCustomerNic').val('');
    $('#txtCustomerAddress').val('');
    $('#imgCustomerProfile').val('');
    $('#txtCustomerUserName').val('');
    $('#txtCustomerPassword').val('');
    $('#txtCustomerRePassword').val('');
})


$('#btn-sign-out').click(event => {
    event.preventDefault();
    $('#header').hide()
    $('#nav-bar').hide()
    $('.body-container').hide()
    $('#login-section').show();


    $('#btn-sign-out').hide();
    $('#header_img').hide();
    $('#btn-open-login').show();
})

$('#btn-open-login').click(event => {
    event.preventDefault();
    $('#header').hide()
    $('#nav-bar').hide()
    $('.body-container').hide()
    $('#login-section').show();
})

// $('#btn-customer-create').click(event=>{

// })

const guestRoleViewSet = () => {
    $('#btn-booking').hide();
    $('#btn-customer').hide();
    $('#btn-user').hide();
}

const customerRoleViewSet = () => {
    // $('#btn-booking').hide();
    $('#btn-customer').hide();
    $('#btn-user').hide();
    $('#btn-booking').show();
}

const userRoleViewSet = () => {
    // $('#btn-booking').hide();
    // $('#btn-customer').hide();
    $('#btn-user').hide();
    $('#btn-booking').show();
    $('#btn-customer').show();
}

const adminRoleViewSet = () => {
    $('#btn-booking').show();
    $('#btn-customer').show();
    $('#btn-user').show();
}