export class BookingController {
    constructor() {
        $('#btn-booking').click((event) => this.handleAllBooking(event));
        $('#addToCart').click(event => this.handleAddVehicleToCart(event));
        $('#selectBookingPackage').change((event) => { this.handleBookingSelectPackage(event) })
        $('#selectBookingGuide').change((event) => { this.handleBookingSelectGuide(event) })
        $('#selectBookingHotelOption').change((event) => { this.handleBookingSelectHotelOption(event) })
        $('#selectBookingVehicle').change((event) => { this.handleBookingSelectVehicle(event) })
        $("#guideDetailsCard").on("click", ".guide_remove", (event) => this.handleBookingSelectGuideRemove(event));
        $("#packageDetailsCard").on("click", ".package_remove", (event) => this.handleBookingSelectPackageRemove(event));
        $("#hotelOptionDetailsCard").on("click", "#hotel_remove", (event) => this.handleBookingSelectHotelRemove(event));
        $("#vehicleDetailsCard").on("click", ".booking_vehicle_image_next", event => this.handleLoadNextVehicleImage(event));
        $("#vehicleDetailsCard").on("click", ".booking_vehicle_image_pre", (event) => this.handleLoadPrevVehicleImage(event));
        $("#vehicle_cart").on("click", ".remove_vehicle", (event) => this.handleLoadSelectVehicleRemove(event));
        $('#txtBookingDownPayment').on("input", (event) => this.handleDownPayment(event));
        $('#add_book').click((event) => this.handleAddBooking(event));
    }
    handlePreBookingTableLoad() {
        const userRole = $('#userRole').val();
        const customerId = $('#userId').val();
        if (userRole == "CUSTOMER") {
            if(customerId){
                $.ajax({
                    type: "GET",
                    url: "http://localhost:8090/booking/api/v1/booking/" + customerId,
                    success: (bookings) => {
                        
                    }
                    ,
                    error: (error) => {
                        console.log(error)
                    }
                });
            }else{
                alert('please relogin')
            }
        }
    }
    handleAddBooking(event) {
        const customerId = $('#userId').val();
        const packageId = $('#selectBookingPackage').val();
        const guideId = $('#selectBookingGuide').val();
        const hotelOptionId = $('#selectBookingHotelOption').val();
        const noOfChildren = $('#txtBookingNoOfChildren').val();
        const noOfAdults = $('#txtBookingNoOfAdults').val();
        const startDate = $('#txtBookingStartDate').val();
        const endDate = $('#txtBookingEndDate').val();
        const status = 'PENDING';// PENDING, ACTIVE, END 
        const downPayment = $('#txtBookingDownPayment').val();
        const date = (new Date()).toISOString();
        const vehicleBookings = [];

        const rows = $('#vehicle_cart').find('tr');
        for (let i = 0; i < rows.length; i++) {
            const column = $(rows[i]).find('td');
            const vehicleId = $(rows[i]).data('vehicle-id');
            const distance = $(column[3]).text();
            const pricePerKM = $(column[4]).text();

            const vehicleBooking = {
                vehicleId,
                distance,
                pricePerKM
            }
            vehicleBookings.push(vehicleBooking);
        }

        // validation

        this.saveBookig({
            customerId,
            packageId,
            guideId,
            hotelOptionId,
            vehicleBookings,
            noOfChildren,
            noOfAdults,
            date,
            startDate,
            endDate,
            status,
            downPayment
        });
    }
    saveBookig(booking) {
        $.ajax({
            type: "POST",
            url: "http://localhost:8090/booking/api/v1/booking",
            data: JSON.stringify(booking),
            contentType: "application/json",
            success: (data) => {
                this.reset();
                alert('booking added')
            },
            error: (error) => {
                console.log(error)
            }
        });
    }
    reset() {
        this.handlePackageLoad();
        this.handleGuideLoad();
        this.handleHotelLoad();
        this.handleVehicleLoad();
        this.handlePreBookingTableLoad();
        $('#packageDetailsCard').empty();
        $('#guideDetailsCard').empty();
        $('#hotelOptionDetailsCard').empty();
        $('#vehicleDetailsCard').empty();
        $('#vehicle_cart').empty();
        $('#txtBookingNoOfChildren').val('');
        $('#txtBookingNoOfAdults').val('');
        $('#txtBookingStartDate').val('');
        $('#txtBookingEndDate').val('');
        $('#txtBookingDownPayment').val('');
        $('#total').val(0);
        $('#subTotal').val(0);
    }
    handleDownPayment(event) {
        event.preventDefault();
        const total = $('#total').text();
        if (total == 0) {
            alert('please fill top fields');
        } else {
            $('#subTotal').text(parseFloat(total) - ($(event.target).val()));
        }
    }
    handleLoadSelectVehicleRemove(event) {
        event.preventDefault();
        const vehicleId = $(event.target.closest('tr')).data('vehicle-id');
        const pricePerKM = $(event.target.closest('tr')).data('price');
        const distance = $(event.target.closest('tr')).data('distance');
        $('#selectBookingVehicle option[value="' + vehicleId + '"]').prop('disabled', false);
        $(event.target.closest('tr')).remove();
        $('#total').text(parseFloat($('#total').text()) - (pricePerKM * distance));
    }
    handleAddVehicleToCart(event) {
        event.preventDefault();
        const optionElm = $('#selectBookingVehicle').find('option:selected');
        const vehicleId = optionElm.val()
        if (vehicleId) {
            const distance = $('#txtBookingVehicleDistance').val();
            if (distance) {
                const vehicleLicenseNumber = optionElm.data('vehicle_license_number');
                const vehicleFrontImage = optionElm.data('vehicle_front_image');
                const brand = optionElm.data('brand');
                const pricePerKM = optionElm.data('price_per_km');
                $('#vehicle_cart').append(
                    `
                    <tr data-vehicle-id="${vehicleId}" data-price="${pricePerKM}" data-distance="${distance}">
                        <td class="col-lg-2">${vehicleLicenseNumber.toUpperCase()}</td>
                        <td class="col-lg-3"><img src="data:image/jpeg;base64, ${vehicleFrontImage}" width="100%"></td>
                        <td class="col-lg-2">${brand.toUpperCase()}</td>
                        <td class="col-lg-2">${distance}</td>
                        <td class="col-lg-2">${pricePerKM}</td>
                        <td class="col-lg-1">
                            <a class="remove_vehicle" href="" >
                                <i class='bx bx-x'></i>
                            </a>
                        </td>
                    </tr>
                    `
                );
                $('#total').text(parseFloat($('#total').text()) + (pricePerKM * distance));
                $('#selectBookingVehicle').prop('selectedIndex', 0);
                $('#txtBookingVehicleDistance').val('');
                $('#vehicleDetailsCard').empty();
                optionElm.prop('disabled', true)
            } else {
                alert('please enter distance');
            }
        } else {
            alert('please select vehicle');
        }
    }
    handleLoadPrevVehicleImage(event) {
        event.preventDefault();
        const pre_img = $(event.target.closest('.vehicle_card')).find('.active');
        let nextIndex = parseInt(pre_img.attr('class').split(' ')[0].slice(11, 12)) - 1;
        if (nextIndex < 1) {
            nextIndex = 5;
        }
        pre_img.hide();
        pre_img.removeClass('active');
        $(event.target.closest('.vehicle_card')).find('.vehicle_img' + nextIndex).show();
        $(event.target.closest('.vehicle_card')).find('.vehicle_img' + nextIndex).addClass('active');
    }
    handleLoadNextVehicleImage(event) {
        event.preventDefault();
        const pre_img = $(event.target.closest('.vehicle_card')).find('.active');
        let nextIndex = parseInt(pre_img.attr('class').split(' ')[0].slice(11, 12)) + 1;
        if (nextIndex > 5) {
            nextIndex = 1;
        }
        pre_img.hide();
        pre_img.removeClass('active');
        $(event.target.closest('.vehicle_card')).find('.vehicle_img' + nextIndex).show();
        $(event.target.closest('.vehicle_card')).find('.vehicle_img' + nextIndex).addClass('active');
    }
    handleBookingSelectVehicle(event) {
        event.preventDefault();
        $('#vehicleDetailsCard').empty();
        const optionElm = $(event.target).find('option:selected');
        const vehicleId = optionElm.val();
        if (vehicleId) {
            const vehicleFrontImage = optionElm.data('vehicle_front_image');
            const vehicleRearImage = optionElm.data('vehicle_rear_image');
            const vehicleSideImage = optionElm.data('vehicle_side_image');
            const vehicleFrontInteriorImage = optionElm.data('vehicle_front_interior_image');
            const vehicleRearInteriorImage = optionElm.data('vehicle_rear_interior_image');
            const brand = optionElm.data('brand');
            const category = optionElm.data('category');
            const isHybrid = optionElm.data('is_hybrid');
            const transmission = optionElm.data('transmission');
            const type = optionElm.data('type');
            const fuelType = optionElm.data('fuel_type');
            const pricePerKM = optionElm.data('price_per_km');
            const phone = optionElm.data('phone');
            const fuelUsagePerKM = optionElm.data('fuel_usage_per_km');
            const capacity = optionElm.data('capacity');
            const vehicleLicenseNumber = optionElm.data('vehicle_license_number');
            $('#vehicleDetailsCard').append(`
                <div class="vehicle_card">
                    <img class="vehicle_img1" src="data:image/jpeg;base64, ${vehicleFrontImage}">
                    <img class="vehicle_img2" src="data:image/jpeg;base64, ${vehicleRearImage}">
                    <img class="vehicle_img3" src="data:image/jpeg;base64, ${vehicleSideImage}">
                    <img class="vehicle_img4" src="data:image/jpeg;base64, ${vehicleFrontInteriorImage}">
                    <img class="vehicle_img5" src="data:image/jpeg;base64, ${vehicleRearInteriorImage}">
                    <div class="vehicle_image_action">
                        <a class="booking_vehicle_image_pre" href="">
                            <i class='bx bxs-left-arrow'></i>
                        </a>
                        <a class="booking_vehicle_image_next" href="">
                            <i class='bx bxs-right-arrow'></i>
                        </a>
                    </div>
                    <div class="vehicle_container">
                        <div class="vehicle_brand">
                            ${brand.toUpperCase()}
                        </div>
                        <div class="vehicle_category_isHybrid_transmission_type">
                            ${category} - ${isHybrid ? "Hybrid" : "No Hybrid"} ${transmission} ${type}
                        </div>
                        <div class="vehicle_fuelType">
                            ${fuelType}
                        </div>
                        <div class="vehicle_price">
                            <small>LKR : </small>${pricePerKM} <small>( per km )</small>
                        </div>
                        <div  class="vehicle_phone">${phone}</div>
                        <hr style="margin-bottom: 5px;">
                        <div class="vehicle_card_bottom">
                        <div class="row">
                            <div class="col-4">
                                km/l
                            </div>
                            <div class="col-4">
                                seat
                            </div>
                            <div class="col-4">
                                registration
                            </div>
                            <div class="col-4">
                                ${fuelUsagePerKM}
                            </div>
                            <div class="col-4">
                                ${capacity}
                            </div>
                            <div class="col-4">
                                ${vehicleLicenseNumber}
                            </div>
                        </div>
                        </div>
                    </div>
                </div>
            `);
            $('#txtBookingVehicleDistance').focus();
            $('.vehicle_img1').addClass('active')
            $('.vehicle_img2').hide();
            $('.vehicle_img3').hide();
            $('.vehicle_img4').hide();
            $('.vehicle_img5').hide();
        }
    }
    handleBookingSelectHotelRemove(event) {
        event.preventDefault();
        const prevPrice = $('#hotelOptionDetailsCard .hotel-card').data('price');
        if (prevPrice) {
            $('#total').text(parseFloat($('#total').text()) - (prevPrice));
        }
        $('#selectBookingHotelOption').prop('selectedIndex', 0);
        $('#hotelOptionDetailsCard').empty();
    }
    handleBookingSelectPackageRemove(event) {
        event.preventDefault();
        const prevPrice = $('#packageDetailsCard .guide_card').data('price');
        if (prevPrice) {
            $('#total').text(parseFloat($('#total').text()) - (prevPrice));
        }
        $('#selectBookingPackage').prop('selectedIndex', 0);
        $('#packageDetailsCard').empty();
    }
    handleBookingSelectGuideRemove(event) {
        event.preventDefault();
        const prevPrice = $('#guideDetailsCard .guide_card').data('price');
        if (prevPrice) {
            $('#total').text(parseFloat($('#total').text()) - (prevPrice));
        }
        $('#selectBookingGuide').prop('selectedIndex', 0);
        $('#guideDetailsCard').empty();
    }
    handleBookingSelectHotelOption(event) {
        event.preventDefault();
        const prevPrice = $('#hotelOptionDetailsCard .hotel-card').data('price');
        if (prevPrice) {
            $('#total').text(parseFloat($('#total').text()) - (prevPrice));
        }
        $('#hotelOptionDetailsCard').empty();
        const optionElm = $(event.target).find('option:selected');
        const hotelOptionId = optionElm.val();
        if (hotelOptionId) {
            const price = optionElm.data('price');
            const category = optionElm.data('category');
            const type = optionElm.data('type');
            const capacity = optionElm.data('capacity');
            const name = optionElm.data('name');
            const address = optionElm.data('address');
            const isPetAllowed = optionElm.data('isPetAllowed');
            $('#hotelOptionDetailsCard').append(`
                <div class="hotel-card" data-price="${price}">
                    <div class="inner">
                        <span class="hotel-option-price">
                            <span>
                                <small>LKR : </small> 
                                ${price}
                            </span>
                        </span>
                        <div class="hotel-category">
                            ${('<i class="fas fa-star checked"></i>').repeat(category)}   
                        </div>
                        <p class="hotel-option-type-capacity">
                            ${type} day ${capacity} Room
                        </p>
                        <p class="hotel-name">${name}</p>
                        <p class="hotel-address">${address}</p>
                        <p class="info">
    
                        </p>
                        <ul class="features">
                            <li>
                                <span class="icon">
                                    <svg height="24" width="24" viewBox="0 0 24 24"
                                        xmlns="http://www.w3.org/2000/svg">
                                        <path d="M0 0h24v24H0z" fill="none"></path>
                                        <path fill="currentColor"
                                            d="M10 15.172l9.192-9.193 1.415 1.414L10 18l-6.364-6.364 1.414-1.414z">
                                        </path>
                                    </svg>
                                </span>
                                <span><strong>${capacity == "Double" ? 2 : 3}</strong> members can live</span>
                            </li>
                            <li>
                                <span class="icon">
                                    <svg height="24" width="24" viewBox="0 0 24 24"
                                        xmlns="http://www.w3.org/2000/svg">
                                        <path d="M0 0h24v24H0z" fill="none"></path>
                                        <path fill="currentColor"
                                            d="M10 15.172l9.192-9.193 1.415 1.414L10 18l-6.364-6.364 1.414-1.414z">
                                        </path>
                                    </svg>
                                </span>
                                <span>Pets are <strong>${isPetAllowed ? "allowed" : "not allowed"}</strong></span>
                            </li>
                            <li>
                                <span class="icon">
                                    <svg height="24" width="24" viewBox="0 0 24 24"
                                        xmlns="http://www.w3.org/2000/svg">
                                        <path d="M0 0h24v24H0z" fill="none"></path>
                                        <path fill="currentColor"
                                            d="M10 15.172l9.192-9.193 1.415 1.414L10 18l-6.364-6.364 1.414-1.414z">
                                        </path>
                                    </svg>
                                </span>
                                <span><strong>A/C</strong> Luxury Room</span>
                            </li>
                        </ul>
                        <div class="action">
                            <a href="#" id="hotel_remove" class="button">
                                Remove
                            </a>
                        </div>
                    </div>
            </div>
        `);
            $('#total').text(parseFloat($('#total').text()) + (price));
        }
    }
    handleBookingSelectGuide(event) {
        event.preventDefault();
        const prevPrice = $('#guideDetailsCard .guide_card').data('price');
        if (prevPrice) {
            $('#total').text(parseFloat($('#total').text()) - (prevPrice));
        }
        $('#guideDetailsCard').empty();
        const optionElm = $(event.target).find('option:selected');
        const guideId = optionElm.val();
        if (guideId) {
            const price = optionElm.data('price');
            const phone = optionElm.data('phone');
            const profile = optionElm.data('profile');
            const name = optionElm.data('name');
            const address = optionElm.data('address');
            $('#guideDetailsCard').append(`
                <div class="guide_card" data-price="${price}">
                    <img class="guide_profile" src="data:image/jpeg;base64, ${profile}" alt="guide profile img" style="width:100%">
                    <div class="guide_name">${name}</div>
                    <div class="guide_address">${address}</div>
                    <div class="guide_phone">${phone}</div>
                    <div class="guide_price"><small>LKR : </small>${price} per day</div>
                    <button class="guide_remove">Remove</button>
                </div>
            `);
            $('#total').text(parseFloat($('#total').text()) + (price));
        }
    }
    handleBookingSelectPackage(event) {
        event.preventDefault();
        const prevPrice = $('#packageDetailsCard .guide_card').data('price');
        if (prevPrice) {
            $('#total').text(parseFloat($('#total').text()) - (prevPrice));
        }
        $('#packageDetailsCard').empty();
        const optionElm = $(event.target).find('option:selected');
        const packageId = optionElm.val();
        if (packageId) {
            const category = optionElm.data('category');
            const area = optionElm.data('area');
            const days = optionElm.data('days');
            const price = optionElm.data('package-price');
            $('#packageDetailsCard').append(`
                <div class="guide_card" data-price="${price}">
                    <div class="guide_name">${area}</div>
                    <div class="guide_address">${category}</div>
                    <div class="guide_phone">${days} days</div>
                    <div class="guide_price"><small>LKR : </small>${price} per day</div>
                    <button class="package_remove">Remove</button>
                </div>
            `);
            $('#total').text(parseFloat($('#total').text()) + (price));
        }
    }
    handleAllBooking(event) {
        event.preventDefault();
        this.handlePackageLoad();
        this.handleGuideLoad();
        this.handleHotelLoad();
        this.handleVehicleLoad();
        this.handlePreBookingTableLoad();
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
            <option value="${vehicle.vehicleId}" 
                data-vehicle_front_image="${vehicle.vehicleFrontImage}"
                data-vehicle_rear_image="${vehicle.vehicleRearImage}"
                data-vehicle_side_image="${vehicle.vehicleSideImage}"
                data-vehicle_front_interior_image="${vehicle.vehicleFrontInteriorImage}"
                data-vehicle_rear_interior_image="${vehicle.vehicleRearInteriorImage}"
                data-brand="${vehicle.brand}"
                data-category="${vehicle.category}"
                data-is_hybrid="${vehicle.isHybrid}"
                data-transmission="${vehicle.transmission}"
                data-type="${vehicle.type}"
                data-fuel_type="${vehicle.fuelType}"
                data-price_per_km="${vehicle.pricePerKM}"
                data-phone="${vehicle.phone}"
                data-fuel_usage_per_km="${vehicle.fuelUsagePerKM}"
                data-capacity="${vehicle.capacity}"
                data-vehicle_license_number="${vehicle.vehicleLicenseNumber}"
            >
                ${vehicle.category.charAt(0).toUpperCase() + vehicle.category.slice(1)} - 
                ${vehicle.brand.toUpperCase()} 
                ${vehicle.transmission.charAt(0).toUpperCase() + vehicle.transmission.slice(1)} 
                ${vehicle.type.charAt(0).toUpperCase() + vehicle.type.slice(1)}
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
            <option value="${hotelOption.hotelOptionId}" data-price="${hotelOption.price}" data-category="${hotel.category}" data-type="${hotelOption.type}" data-capacity="${hotelOption.capacity}" data-name="${hotel.name}" data-address="${hotel.address}" data-isPetAllowed="${hotel.isPetAllowed}">${hotel.name} - ${hotel.address} ${(hotel.category == 2) ? "Two Star" :
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
            <option value="${guide.guideId}" data-price="${guide.pricePerDay}" data-phone=${guide.phone} data-profile="${guide.profile}" data-name="${guide.name}" data-address="${guide.address}">${guide.name} - ${guide.address}</option>
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
            <option value="${aPackage.packageId}" data-category="${aPackage.category}" data-area="${aPackage.area}" data-days="${aPackage.averageNoOfDays}" data-package-price="${aPackage.price}">${aPackage.category} Package ${aPackage.area} ${aPackage.averageNoOfDays} days</option>
        `;
    }

}

new BookingController();