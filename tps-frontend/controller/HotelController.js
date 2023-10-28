export class HotelController {
    constructor() {
        $('#btn-hotel').click((event) => {
            event.preventDefault();
            this.handleLoadAllHotel();
        });
        $('.hotel-more-about').click((event) => {
            event.preventDefault();
            this.handleViewHotelDetails();
        })
        $('#btnSaveHotel').click((event) => {
            event.preventDefault();
            this.handleValidateHotel();
        })
        $('#btnResetHotel').click((event) => {
            event.preventDefault();
            this.reset();
        })
    }
    handleLoadAllHotel() {

    }
    handleViewHotelDetails() {

    }
    handleValidateHotel() {
        const name = $('#txtHotelName').val();
        const category = $('#txtHotelCategory').val();
        const address = $('#txtHotelAddress').val();
        const location = $('#txtHotelLocation').val();
        const email = $('#txtHotelEmail').val();
        const phone = $('#txtHotelPhone').val();
        const isPetAllowed = $('#txtHotelIsPetAllowed:checked').is(":checked");
        const cancelCriteria = $('#txtHotelCancelCriteria').val();

        // validation
        console.log(isPetAllowed)

        this.handleSaveHotel({
            name,
            category,
            address,
            location,
            email,
            phone,
            isPetAllowed,
            cancelCriteria
        });
    }
    handleSaveHotel(hotel) {
        console.log(hotel)
        $.ajax({
            type: "POST",
            url: "http://localhost:8090/hotel/api/v1/hotel",
            data: JSON.stringify(hotel),
            contentType: "application/json",
            success: (data) => {
                console.log(data)
                this.handleLoadAllHotel()
                this.reset();
            },
            error: (error) => {
                console.log(error)
            }
        });
    }
    reset() {
        $('#txtHotelName').val('');
        $('#txtHotelCategory').val('Select Category');
        $('#txtHotelAddress').val('');
        $('#txtHotelLocation').val('');
        $('#txtHotelEmail').val('');
        $('#txtHotelPhone').val('');
        $('#txtHotelIsPetAllowed').prop('checked', false);
        $('#txtHotelCancelCriteria').val('');
    }
}

new HotelController();