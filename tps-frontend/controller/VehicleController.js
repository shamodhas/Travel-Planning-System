export class VehicleController {
    constructor() {
        $('#btn-vehicle').click((event) => {
            event.preventDefault();
            this.handleLoadAllVehicle();
        });
        $('#btnSaveVehicle').click((event) => {
            event.preventDefault();
            this.handleValidateVehicle();
        });
        $('#btnResetVehicle').click((event) => {
            event.preventDefault();
            this.reset();
        });
        $("#vehicle-body .row").on("click", ".vehicle_card .vehicle_image_next", (event) => {
            event.preventDefault();
            this.handleLoadNextImage(event);
        });
        $("#vehicle-body .row").on("click", ".vehicle_card .vehicle_image_pre", (event) => {
            event.preventDefault();
            this.handleLoadPrevImage(event);
        });
        $("#vehicle-body .row").on("click", ".vehicle_card .vehicle_view_more", (event) => {
            event.preventDefault();
            this.handleLoadMoreVehicleDetails(event);
        });
    }
    handleLoadMoreVehicleDetails(event){
        const vehicleId = $(event.target.closest('.vehicle_card')).find('.vehicle_id').val();

        $('#vehicle_details_body').empty();
        $('#vehicle_driver_details_body').empty();

        $.ajax({
            type: "GET",
            url: "http://localhost:8090/vehicle/api/v1/vehicle/" + vehicleId,
            success: (vehicle) => {
                $('#vehicle_details_body').append(`
                    <div class="vehicle_details_body">Vehicle License Number : ${vehicle.vehicleLicenseNumber.toUpperCase()}</div>
                    <div class="vehicle_details_body">Brand : ${vehicle.brand.toUpperCase()}</div>
                    <div class="vehicle_details_body">Category : ${vehicle.category}</div>
                    <div class="vehicle_details_body">Fuel Type : ${vehicle.fuelType}</div>
                    <div class="vehicle_details_body">Is Hybrid : ${vehicle.isHybrid?"Hybrid":"No Hybrid"}</div>
                    <div class="vehicle_details_body">Fuel Usage Per KM : ${vehicle.fuelUsagePerKM} km/l</div>
                    <div class="vehicle_details_body">Price Per KM : LKR ${vehicle.pricePerKM}</div>
                    <div class="vehicle_details_body">Capacity : ${vehicle.capacity} seat</div>
                    <div class="vehicle_details_body">Type : ${vehicle.type}</div>
                    <div class="vehicle_details_body">Transmission : ${vehicle.transmission}</div>
                `);
                $('#vehicle_driver_details_body').append(`
                    <div class="vehicle_driver_details_body">Driver name : ${vehicle.driverName}</div>
                    <div class="vehicle_driver_details_body">Phone : ${vehicle.phone}</div>
                    <div class="vehicle_driver_details_body">
                        <img src="data:image/jpeg;base64, ${vehicle.driverLicenseFrontImage}" alt="driver License Front Image">
                    </div>
                    <div class="vehicle_driver_details_body">
                        <img src="data:image/jpeg;base64, ${vehicle.driverLicenseBackImage}" alt="driver License Back Image">
                    </div>
                `);
            }
            ,
            error: (error) => {
                console.log(error)
            }
        })
    }
    handleLoadPrevImage(event) {
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
    handleLoadNextImage(event) {
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
    handleValidateVehicle() {
        const vehicleLicenseNumber = $('#txtVehicleLicenseNumber').val();
        const brand = $('#txtVehicleBrand').val();
        const category = $('#txtVehicleCategory').val();
        const fuelType = $('#txtVehicleFuelType').val();
        const isHybrid = $('#txtVehicleIsHybrid:checked').is(":checked");
        const fuelUsagePerKM = $('#txtVehicleFuelUsagePerKM').val();
        const vehicleFrontImage = $('#txtVehicleFrontImage')[0].files[0];
        const vehicleRearImage = $('#txtVehicleRearImage')[0].files[0];
        const vehicleSideImage = $('#txtVehicleSideImage')[0].files[0];
        const vehicleFrontInteriorImage = $('#txtVehicleFrontInteriorImage')[0].files[0];
        const vehicleRearInteriorImage = $('#txtVehicleRearInteriorImage')[0].files[0];
        const pricePerKM = $('#txtVehiclePricePerKM').val();
        const capacity = $('#txtVehicleCapacity').val();
        const type = $('#txtVehicleType').val();
        const transmission = $('#txtVehicleTransmission').val();
        const driverName = $('#txtVehicleDriverName').val();
        const driverLicenseFrontImage = $('#txtVehicleDriverLicenseFrontImage')[0].files[0];
        const driverLicenseBackImage = $('#txtVehicleDriverLicenseBackImage')[0].files[0];
        const phone = $('#txtVehiclePhone').val();

        // validation

        var formData = new FormData();

        formData.append('vehicleLicenseNumber', vehicleLicenseNumber);
        formData.append('brand', brand);
        formData.append('category', category);
        formData.append('fuelType', fuelType);
        formData.append('isHybrid', isHybrid);
        formData.append('fuelUsagePerKM', fuelUsagePerKM);
        formData.append('vehicleFrontImage', vehicleFrontImage);
        formData.append('vehicleRearImage', vehicleRearImage);
        formData.append('vehicleSideImage', vehicleSideImage);
        formData.append('vehicleFrontInteriorImage', vehicleFrontInteriorImage);
        formData.append('vehicleRearInteriorImage', vehicleRearInteriorImage);
        formData.append('pricePerKM', pricePerKM);
        formData.append('capacity', capacity);
        formData.append('type', type);
        formData.append('transmission', transmission);
        formData.append('driverName', driverName);
        formData.append('driverLicenseFrontImage', driverLicenseFrontImage);
        formData.append('driverLicenseBackImage', driverLicenseBackImage);
        formData.append('phone', phone);

        this.handleSaveVehicle(formData);
    }
    handleSaveVehicle(vehicle) {
        $.ajax({
            type: "POST",
            url: "http://localhost:8090/vehicle/api/v1/vehicle",
            data: vehicle,
            contentType: false,
            processData: false,
            success: (data) => {
                this.handleLoadAllVehicle();
                this.reset();
            },
            error: (error) => {
                console.log(error)
            }
        })
    }
    handleLoadAllVehicle() {
        $.ajax({
            type: "GET",
            url: "http://localhost:8090/vehicle/api/v1/vehicle/public",
            success: (response) => {
                this.handleLoadAllVehicleCard(response);
            }
            ,
            error: (error) => {
                console.log(error)
            }
        })
    }
    handleLoadAllVehicleCard(vehicles) {
        $('#vehicle-body .row').empty();
        $('#vehicle-body .row').append(vehicles?.map(
            (vehicle) => this.renderVehicleCard(vehicle)
        ).join(''));
        $('.vehicle_img1').addClass('active')
        $('.vehicle_img2').hide();
        $('.vehicle_img3').hide();
        $('.vehicle_img4').hide();
        $('.vehicle_img5').hide();
    }
    renderVehicleCard(vehicle) {
        return `
            <div class="col-lg-4 col-md-6">
                <div class="vehicle_card">
                    <input class="vehicle_id" type="hidden" value="${vehicle.vehicleId}">
                    <img class="vehicle_img1" src="data:image/jpeg;base64, ${vehicle.vehicleFrontImage}">
                    <img class="vehicle_img2" src="data:image/jpeg;base64, ${vehicle.vehicleRearImage}">
                    <img class="vehicle_img3" src="data:image/jpeg;base64, ${vehicle.vehicleSideImage}">
                    <img class="vehicle_img4" src="data:image/jpeg;base64, ${vehicle.vehicleFrontInteriorImage}">
                    <img class="vehicle_img5" src="data:image/jpeg;base64, ${vehicle.vehicleRearInteriorImage}">
                    <div class="vehicle_image_action">
                        <a class="vehicle_image_pre" href="">
                            <i class='bx bxs-left-arrow'></i>
                        </a>
                        <a class="vehicle_image_next" href="">
                            <i class='bx bxs-right-arrow'></i>
                        </a>
                    </div>
                    <div class="vehicle_container">
                        <div class="vehicle_brand">
                            ${vehicle.brand.toUpperCase()}
                        </div>
                        <div class="vehicle_category_isHybrid_transmission_type">
                            ${vehicle.category} - ${vehicle.isHybrid ? "Hybrid" : "No Hybrid"} ${vehicle.transmission} ${vehicle.type}
                        </div>
                        <div class="vehicle_fuelType">
                            ${vehicle.fuelType}
                        </div>
                        <div class="vehicle_price">
                            <small>LKR : </small>${vehicle.pricePerKM} <small>( per km )</small>
                        </div>
                        <a href="" class="btn btn_vehicle_action btn-primary ">
                            Book Now
                        </a>
                        <div  class="vehicle_phone">${vehicle.phone}</div>
                        <a href=""  data-bs-toggle="modal" data-bs-target="#vehicleDetailsModal" class="vehicle_view_more">
                            view more details
                        </a>
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
                                ${vehicle.fuelUsagePerKM}
                            </div>
                            <div class="col-4">
                                ${vehicle.capacity}
                            </div>
                            <div class="col-4">
                                ${vehicle.vehicleLicenseNumber.toUpperCase()}
                            </div>
                        </div>
                        </div>
                    </div>
                </div>
            </div>
        `;
    }
    reset() {
        $('#txtVehicleLicenseNumber').val('');
        $('#txtVehicleBrand').val('');
        $('#txtVehicleCategory').val('');
        $('#txtVehicleFuelType').val('');
        $('#txtVehicleIsHybrid:checked').prop('checked', false);
        $('#txtVehicleFuelUsagePerKM').val('');
        $('#txtVehicleFrontImage').val('');
        $('#txtVehicleRearImage').val('');
        $('#txtVehicleSideImage').val('');
        $('#txtVehicleFrontInteriorImage').val('');
        $('#txtVehicleRearInteriorImage').val('');
        $('#txtVehiclePricePerKM').val('');
        $('#txtVehicleCapacity').val('');
        $('#txtVehicleType').val('');
        $('#txtVehicleTransmission').val('');
        $('#txtVehicleDriverName').val('');
        $('#txtVehicleDriverLicenseFrontImage').val('');
        $('#txtVehicleDriverLicenseBackImage').val('');
        $('#txtVehiclePhone').val('');

    }
}

new VehicleController();