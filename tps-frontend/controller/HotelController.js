export class HotelController {
    constructor() {
        this.hotels = [];
        $('#btn-hotel').click((event) => {
            event.preventDefault();
            this.handleHotelDetailsViewHide();
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
        $("#tblHotelBody").on("click", "tr", (event) => {
            event.preventDefault();
            this.handleHotelTableSelect(event);
        });
        $("#hotel-body .row").on("click", ".hotel-card .hotel-more-about", (event) => {
            event.preventDefault();
            this.handleHotelDetailsView(event);
        });
        $('#close-hotel-details-view').click((event) => {
            event.preventDefault();
            this.handleHotelDetailsViewHide();
        })
        $('#txtHotelNamewithAddress').change((event) => {
            event.preventDefault();
            this.handleHotelOptionSelectHotel(event);
        });
        $("#hotelOptionTableBody").on("click", "tr", (event) => {
            event.preventDefault();
            this.handleHotelOptionTableSelectRow(event);
        });
        $('#btnSaveHotelOption').click((event) => {
            event.preventDefault();
            this.hadleValidateHotelOption();
        })
        $('#btnResetHotelOption').click((event) => {
            event.preventDefault();
            this.resetHotelOption();
        })
    }
    hadleValidateHotelOption() {
        const hotelId = $('#txtHotelNamewithAddress').val();
        const type = $('#txtHotelOptionType').val();
        const capacity = $('#txtHotelOptionCapacity').val();
        const price = $('#txtHotelOptionPrice').val();

        this.hadleSaveHotelOption({
            hotelId,
            type,
            capacity,
            price
        });
    }
    hadleSaveHotelOption(hotelOption) {
        $.ajax({
            type: "POST",
            url: "http://localhost:8090/hotel/api/v1/hotelOption",
            data: JSON.stringify(hotelOption),
            contentType: "application/json",
            success: (data) => {
                this.handleLoadAllHotel()
                this.resetHotelOption();
            },
            error: (error) => {
                console.log(error)
            }
        });
    }
    handleHotelOptionTableSelectRow(event) {
        const hotelId = $(event.currentTarget).data('hotel-id');
        const hotelOptionId = $(event.currentTarget).data('hotel-option-id');
        const type = $(event.currentTarget).find('th:nth-child(1)').text().split(' ').shift();
        const capacity = $(event.currentTarget).find('th:nth-child(2)').text().split(' ').shift();
        const price = $(event.currentTarget).find('th:nth-child(3)').text().split(' ')[2];

        $('#txtHotelOptionType').val(type);
        $('#txtHotelOptionCapacity').val(capacity);
        $('#txtHotelOptionPrice').val(price);
        $('#txtHotelOptionId').val(hotelOptionId);
        $('#txtOptionFormHotelId').val(hotelId);
    }
    handleHotelOptionSelectHotel(event) {
        const hotelId = $(event.currentTarget).val();
        if (hotelId)
            this.renderHotelOptionTable(hotelId);
    }
    handleHotelDetailsViewHide() {
        $('#hotel-details-view').hide();
        $('html, body').css({ 'overflow-y': 'auto' });
    }
    handleHotelDetailsView(event) {
        $('#hotel-details-view').show();
        $('#hotel-details-view').css({ 'top': `${window.scrollY}px` });
        $('html, body').css({ 'overflow-y': 'hidden' });

        const hotelId = $(event.target.closest('.hotel-card')).data('hotel-id')
        $.ajax({
            type: "GET",
            url: "http://localhost:8090/hotel/api/v1/hotel/" + hotelId,
            success: (response) => {
                if (response) {
                    $('.hotel-details-name').text(response.name);
                    $('.hotel-details-address').text(response.address);
                    $('.hotel-details-category').empty();
                    $('.hotel-details-category').append(('<i class="fas fa-star checked"></i>').repeat(response.category));
                    $('.hotel-details-location').text(response.location);
                    $('.hotel-details-email').text(response.email);
                    $('.hotel-details-phone').text(response.phone);
                    $('.hotel-details-pet').text(response.isPetAllowed ? "Pets allowed" : "Pets not allowed");
                    $('.hotel-details-criteria').text(response.cancelCriteria);
                } else {
                    $('.hotel-details-name').text('');
                    $('.hotel-details-address').text('');
                    $('.hotel-details-category').empty();
                    $('.hotel-details-location').text('');
                    $('.hotel-details-email').text('');
                    $('.hotel-details-phone').text('');
                    $('.hotel-details-pet').text('');
                    $('.hotel-details-criteria').text('');
                }
            }
            ,
            error: (error) => {
                console.log(error)
            }
        })

    }
    handleHotelTableSelect(event) {
        const selectedRow = $(event.currentTarget);

        const hotelId = selectedRow.data("hotel-id");
        const name = selectedRow.find("th:nth-child(1)").text();
        const category = selectedRow.find("th:nth-child(2)").find('i').length;
        const address = selectedRow.find("th:nth-child(3)").text();
        const location = selectedRow.find("th:nth-child(4)").text();
        const email = selectedRow.find("th:nth-child(5)").text();
        const phone = selectedRow.find("th:nth-child(6)").text();
        const isPetAllowed = selectedRow.find("th:nth-child(7)").text() == 'yes' ? true : false;
        const cancelCriteria = selectedRow.find("th:nth-child(8)").text();

        $('#txtHotelId').val(hotelId);
        $('#txtHotelName').val(name);
        $('#txtHotelCategory').val(category);
        $('#txtHotelAddress').val(address);
        $('#txtHotelLocation').val(location);
        $('#txtHotelEmail').val(email);
        $('#txtHotelPhone').val(phone);
        $('#txtHotelIsPetAllowed').prop('checked', isPetAllowed);
        $('#txtHotelCancelCriteria').val(cancelCriteria);

        $('#txtHotelNamewithAddress').val(hotelId);
        $('#lblHotelNameWithAddress').text(name + " - " + address);
        this.renderHotelOptionTable(hotelId);

    }
    renderHotelOptionTable(hotelId) {
        $('#hotelOptionTableBody').empty();
        $.ajax({
            type: "GET",
            url: "http://localhost:8090/hotel/api/v1/hotel/" + hotelId,
            success: (response) => {
                $('#hotelOptionTableBody').append(response.hotelOptions?.map(this.renderHotelOptionRow).join(''));
                window.scrollTo({
                    top: 0,
                    behavior: 'smooth'
                });
            }
            ,
            error: (error) => {
                console.log(error)
            }
        })
    }
    renderHotelOptionRow(hotelOption) {
        return `
        <tr data-hotel-id=${hotelOption.hotelId} data-hotel-option-id=${hotelOption.hotelOptionId} class="text-center">
            <th scope="col">${hotelOption.type} Day</th>
            <th scope="col">${hotelOption.capacity} Room</th>
            <th scope="col"><small>LKR : </small>${hotelOption.price}</th>
        </tr>
        `;
    }
    handleLoadAllHotel() {
        $.ajax({
            type: "GET",
            url: "http://localhost:8090/hotel/api/v1/hotel/public",
            success: (response) => {
                this.hotels = response;
                this.handleLoadAllHotelSelectBox(this.hotels);
                this.handleLoadAllHotelTable(this.hotels);
                this.handleLoadAllHotelCard(this.hotels);
            }
            ,
            error: (error) => {
                console.log(error)
            }
        });
    }
    handleLoadAllHotelSelectBox(hotels) {
        $('#txtHotelNamewithAddress').empty();
        $('#txtHotelNamewithAddress').append(`<option value="" selected>Select Hotel</option>`);
        $('#txtHotelNamewithAddress').append(hotels?.map(this.renderHotelSelectOptions).join(''));
    }
    renderHotelSelectOptions(hotel) {
        return `
            <option value="${hotel.hotelId}">
                ${hotel.name} - ${hotel.address}
            </option>
        `;
    }
    handleLoadAllHotelTable(hotels) {
        $('#tblHotelBody').empty();
        $('#tblHotelBody').append(hotels?.map(this.renderHotelRow).join(''));
    }
    renderHotelRow(hotel) {
        return `
            <tr data-hotel-id=${hotel.hotelId} class="text-center">
                <th scope="col">${hotel.name}</th>
                <th scope="col">${('<i class="fas fa-star checked"></i>').repeat(hotel.category)}</th>
                <th scope="col">${hotel.address}</th>
                <th scope="col">${hotel.location}</th>
                <th scope="col">${hotel.email}</th>
                <th scope="col">${hotel.phone}</th>
                <th scope="col">${hotel.isPetAllowed ? "yes" : "no"}</th>
                <th scope="col">${hotel.cancelCriteria}</th>
            </tr>
        `;
    }
    handleLoadAllHotelCard(hotels) {
        $('#hotel-body .row').empty();
        hotels?.map((hotel) => {
            $('#hotel-body .row').append(hotel.hotelOptions?.map(
                (hotelOption) => this.renderHotelCard(hotelOption, hotel)
            ).join(''));
        })
    }
    renderHotelCard(hotelOption, hotel) {
        return `
        <div class="col-lg-4 col-md-5 col-sm-6">
            <div class="hotel-card" data-hotel-id=${hotel.hotelId} data-hotel-option-id=${hotelOption.hotelOptionId}>
                <div class="inner">
                    <span class="hotel-option-price">
                        <span>
                            <small>LKR : </small> 
                            ${hotelOption.price}
                        </span>
                    </span>
                    <div class="hotel-category">
                        ${('<i class="fas fa-star checked"></i>').repeat(hotel.category)}   
                    </div>
                    <p class="hotel-option-type-capacity">
                        ${hotelOption.type} day ${hotelOption.capacity} Room
                    </p>
                    <p class="hotel-name">${hotel.name}</p>
                    <p class="hotel-address">${hotel.address}</p>
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
                            <span><strong>${hotelOption.capacity == "Double" ? 2 : 3}</strong> members can live</span>
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
                            <span>Pets are <strong>${hotel.isPetAllowed ? "allowed" : "not allowed"}</strong></span>
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
                            <span>
                                <a href="#" class="hotel-more-about">
                                    view more about hotel
                                </a>
                            </span>
                        </li>
                    </ul>
                    <div class="action">
                        <a href="" class="button">
                            Book Now
                        </a>
                    </div>
                </div>
            </div>
        </div>
    `
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
        $.ajax({
            type: "POST",
            url: "http://localhost:8090/hotel/api/v1/hotel",
            data: JSON.stringify(hotel),
            contentType: "application/json",
            success: (data) => {
                this.handleLoadAllHotel()
                this.reset();
            },
            error: (error) => {
                console.log(error)
            }
        });
    }
    reset() {
        $('#txtHotelId').val('');
        $('#txtHotelName').val('');
        $('#txtHotelCategory').val('Select Category');
        $('#txtHotelAddress').val('');
        $('#txtHotelLocation').val('');
        $('#txtHotelEmail').val('');
        $('#txtHotelPhone').val('');
        $('#txtHotelIsPetAllowed').prop('checked', false);
        $('#txtHotelCancelCriteria').val('');
    }
    resetHotelOption() {
        $('#txtHotelNamewithAddress').val('');
        $('#txtHotelOptionType').val('Select Type');
        $('#txtHotelOptionCapacity').val('Select Capacity');
        $('#txtHotelOptionPrice').val('');
        $('#txtHotelOptionId').val('');
        $('#txtOptionFormHotelId').val('');
        $('#hotelOptionTableBody').empty();
    }
}

new HotelController();