$(document).ready(function (event) {
    init();
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
    linkColor.forEach(l => l.addEventListener('click', colorLink));

    document.getElementById("btn-vehicle").click();
    // $('#vehicle-manage').hide();
    
});

const init = () => {
    $('#login-section').hide();
    $('#customerImageChooser').hide();
    $('#btn-sign-out').hide();
    $('#header_img').hide();
    $('#btn-booking').hide();
    $('#btn-customer').hide();
    $('#btn-user').hide();
    navigate('dashboard');
    $('#btn-open-login').show();
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
    $('#customerImageChooser').hide();
})

$('#goCustomerImageChooser').click(event => {
    $('#customerImageChooser').show();
})
$('#cropped-image-done').click(event => {
    if ($('#cropped-image').attr('src')) {
        $('#customerImageChooser').hide();
    } else {
        alert('please select and crop image')
    }
})
$('#cropped-image-close').click(event => {
    if (cropper) {
        cropper.destroy();
    }
    $('#customer-img-file-input').val('');
    $('#customer-img-for-crop').attr('src', "");
    $('#cropped-image').attr('src', "");
    $('#customerImageChooser').hide();
})

$('#btn-go-login').click(event => {
    event.preventDefault();
    $('#register-form').css('display', 'none');
    $('#login-form').css('display', 'block');
    $('#customerImageChooser').hide();
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
    $('#customerImageChooser').hide();
    $('#btn-sign-out').hide();
    $('#header_img').hide();
    $('#login-section').show();
    $('#btn-open-login').show();
})

$('#btn-open-login').click(event => {
    event.preventDefault();
    $('#customerImageChooser').hide();
    $('#header').hide()
    $('#nav-bar').hide()
    $('.body-container').hide()
    $('#login-section').show();
})



const image = document.getElementById('customer-img-for-crop');
const croppedImage = document.getElementById('cropped-image');
const croppedImageContainer = document.getElementById('cropped-image-container');
let cropper;
$('#customer-img-file-input').change(event => {
    event.preventDefault();
    const selectedFile = event.target.files[0];
    if (selectedFile) {
        const reader = new FileReader();
        reader.onload = function (e) {
            image.src = e.target.result;

            if (cropper) {
                // Destroy the previous cropper instance
                cropper.destroy();
            }

            // Initialize the Cropper
            cropper = new Cropper(image, {
                aspectRatio: 1,
                viewMode: 1,
            });
        };
        reader.readAsDataURL(selectedFile);
    }
})
$('#crop-button').click(event => {
    if (cropper) {
        // Get the cropped data
        const croppedData = cropper.getData();

        // Create a canvas to draw the cropped image
        const canvas = document.createElement('canvas');
        const context = canvas.getContext('2d');

        canvas.width = croppedData.width;
        canvas.height = croppedData.height;

        context.drawImage(image, croppedData.x, croppedData.y, croppedData.width, croppedData.height, 0, 0, croppedData.width, croppedData.height);

        // Convert the canvas to a data URL
        const croppedDataURL = canvas.toDataURL('image/jpeg');

        // Display the cropped image
        croppedImage.src = croppedDataURL;
    } else {
        alert('Please select an image and crop it before displaying the result.');
    }
})