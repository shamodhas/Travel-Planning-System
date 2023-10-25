
document.addEventListener("DOMContentLoaded", function (event) {
    navigate('dashboard')
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

$('.nav .nav_link').click(event => {
    event.preventDefault(); // Prevent the link from navigating
    var text = $(event.currentTarget).find('.nav_name').text();
    $('#header-text').text(text);
    navigate(text);
})

const navigate = (text) => {
    $('.body-container').css('display', 'none');
    $('#' + text.toLowerCase() + '-section').css("display", "block");
}

$('#btn-go-customer-reg').click(event=>{
    event.preventDefault(); 
    $('#login-form').css('display','none');
    $('#regester-form').css('display','block');
})
$('#btn-go-customer-login').click(event=>{
    event.preventDefault(); 
    $('#regester-form').css('display','none');
    $('#login-form').css('display','block');
})


// $('#btn-customer-login').click(event=>{

// })

// $('#btn-customer-create').click(event=>{
    
// })