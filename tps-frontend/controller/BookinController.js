export class BookingController {
    constructor() {
        $('#btn-booking').click((event) => {
            event.preventDefault();
            this.handleAllBooking(event);
        });
        $('#addToCart').click(event => this.handleAddVehicleToCart(event));
    }
    handleAddVehicleToCart(event) {
        event.preventDefault();

    }
    handleAllBooking(event) {
        // get all booking
        this.handlePackageLoad();
        this.handleGuideLoad();
        this.handleHotelLoad();
        this.handleVehicleLoad();
        this.handlePreBookingTableLoad();
    }
    handlePreBookingTableLoad() {

    }
    handleVehicleLoad() {
        $('#selectBookingVehicle').empty();
        $('#selectBookingVehicle').append(`<option value="" selected>Select Vehicle</option>`);
        $.ajax({
            type: "GET",
            url: "http://localhost:8090/vehicle/api/v1/vehicle/public",
            success: (vehicles) => {
                vehicles?.map(vehicle => $('#selectBookingVehicle').append(this.renderBookingVehicleSelectOptions(vehicle)));
            }
            ,
            error: (error) => {
                console.log(error)
            }
        })
    }
    renderBookingVehicleSelectOptions(vehicle) {
        return `
            <option value="${vehicle.vehicleId}">
                ${vehicle.category.charAt(0).toUpperCase()+vehicle.category.slice(1)} - 
                ${vehicle.brand.toUpperCase()} 
                ${vehicle.transmission.charAt(0).toUpperCase() + vehicle.transmission.slice(1)} 
                ${vehicle.type.charAt(0).toUpperCase()+vehicle.type.slice(1)}
            </option>
        `;
    }
    handleHotelLoad() {
        $('#selectBookingHotelOption').empty();
        $('#selectBookingHotelOption').append(`<option value="" selected>Select Hotel Option</option>`);
        $.ajax({
            type: "GET",
            url: "http://localhost:8090/hotel/api/v1/hotel/public",
            success: (hotels) => {
                hotels?.map(hotel => this.handleLoadAllHotelOption(hotel));
            }
            ,
            error: (error) => {
                console.log(error)
            }
        })
    }
    handleLoadAllHotelOption(hotel) {
        hotel.hotelOptions?.map((hotelOption) => {
            $('#selectBookingHotelOption').append(this.renderBookingHotelSelectOptions(hotel, hotelOption));
        });
    }
    renderBookingHotelSelectOptions(hotel, hotelOption) {
        return `
            <option value="${hotelOption.hotelOptionId}">${hotel.name} - ${hotel.address} ${(hotel.category == 2) ? "Two Star" :
                (hotel.category == 3) ? "Tree Star" :
                    (hotel.category == 4) ? "Four Star" :
                        (hotel.category == 5) ? "Five Star" :
                            (hotel.category == 6) ? "Six Star" :
                                "No Star"
            } Hotel ${hotelOption.type} day ${hotelOption.capacity} Room</option>
        `;
    }
    handleGuideLoad() {
        $('#selectBookingGuide').empty();
        $('#selectBookingGuide').append(`<option value="" selected>Select Guide</option>`);
        $.ajax({
            type: "GET",
            url: "http://localhost:8090/guide/api/v1/guide/public",
            success: (guides) => {
                guides?.map(guide => $('#selectBookingGuide').append(this.renderBookingGuideSelectOptions(guide)));
            }
            ,
            error: (error) => {
                console.log(error)
            }
        })
    }
    renderBookingGuideSelectOptions(guide) {
        return `
            <option value="${guide.guideId}">${guide.name} - ${guide.address}</option>
        `;
    }
    handlePackageLoad() {
        $('#selectBookingPackage').empty();
        $('#selectBookingPackage').append(`<option value="" selected>Select Package</option>`);
        $.ajax({
            type: "GET",
            url: "http://localhost:8090/package/api/v1/package/public",
            success: (packages) => {
                this.handleLoadAllPackage(packages);
            }
            ,
            error: (error) => {
                console.log(error)
            }
        })

    }
    handleLoadAllPackage(packages) {
        packages?.map(aPackage => $('#selectBookingPackage').append(this.renderBookingPackageSelectOptions(aPackage)));
    }
    renderBookingPackageSelectOptions(aPackage) {
        return `
            <option value="${aPackage.packageId}">${aPackage.category} Package ${aPackage.area} ${aPackage.averageNoOfDays} days</option>
        `;
    }

}

new BookingController();