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