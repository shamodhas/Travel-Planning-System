export class GuideController {
    constructor() {
        $('#btn-guide').click((event) => {
            event.preventDefault();
            this.handleLoadAllGuide();
        });
        $('#btnSaveGuide').click((event) => {
            event.preventDefault();
            this.handleValidateGuide();
        })
        $('#btnResetGuide').click((event) => {
            event.preventDefault();
            this.reset();
        })
        $("#guide-body .row").on("click", ".guide_card .guide_more_about", (event) => {
            event.preventDefault();
            this.handleLoadMoreGuideDetails(event);
        });
        $('#btn_guide_edit').click((event)=>{
            event.preventDefault();
            this.handleGuideSelect($(event.target.closest('#guideDetailsModal')).find('#more_details_guide_id').val());
        })
    }
    handleGuideSelect(guideId){
        if(guideId){
            $.ajax({
                type: "GET",
                url: "http://localhost:8090/guide/api/v1/guide/" + guideId,
                success: (guide) => {
                    this.setGuideFormFields(guide);
                }
                ,
                error: (error) => {
                    console.log(error)
                }
            })
        }else{
            alert("Wrong selection");
        }
    }
    setGuideFormFields(guide){
        $('#txtGuideId').val(guide.guideId);
        $('#txtGuideName').val(guide.name);
        $('#txtGuideAddress').val(guide.address);
        $('#txtGuideNic').val(guide.nic);
        $('#txtGuidePhone').val(guide.phone);

        // $('#txtGuideProfile').val();
        // $('#txtGuideIdCardFront').val('');
        // $('#txtGuideIdCardBack').val('');
        $('#txtGuidePricePerDay').val(guide.pricePerDay);
    }
    handleLoadMoreGuideDetails(event){
        const guideId = $(event.target.closest('.guide_card')).find('#guide_id').val();
        
        $('#guide_details_body').empty();
        $('#guide_nic_details_body').empty();

        $.ajax({
            type: "GET",
            url: "http://localhost:8090/guide/api/v1/guide/" + guideId,
            success: (guide) => {
                $('#guide_details_body').append(`
                    <input id="more_details_guide_id" type="hidden" value="${guide.guideId}">
                    <div class="guide_details_name">Name : ${guide.name}</div>
                    <div class="guide_details_address">Address : ${guide.address}</div>
                    <div class="guide_details_nic">Nic : ${guide.nic}</div>
                    <div class="guide_details_phone">Phone : ${guide.phone}</div>
                    <div class="guide_details_price">Price per day : LKR ${guide.pricePerDay}</div>
                `);
                $('#guide_nic_details_body').append(`
                    <div>Profile</div>
                    <img src="data:image/jpeg;base64, ${guide.profile}">
                    <div>Id Card Front</div>
                    <img src="data:image/jpeg;base64, ${guide.idCardFront}">
                    <div>Id Card Back</div>
                    <img src="data:image/jpeg;base64, ${guide.idCardBack}">
                `);
            }
            ,
            error: (error) => {
                console.log(error)
            }
        })
    }
    handleValidateGuide() {
        const name = $('#txtGuideName').val();
        const address = $('#txtGuideAddress').val();
        const nic = $('#txtGuideNic').val();
        const phone = $('#txtGuidePhone').val();
        const profile = $('#txtGuideProfile')[0].files[0];
        const idCardFront = $('#txtGuideIdCardFront')[0].files[0];
        const idCardBack = $('#txtGuideIdCardBack')[0].files[0];
        const pricePerDay = $('#txtGuidePricePerDay').val();

        // validation

        var formData = new FormData();

        formData.append('name', name);
        formData.append('address', address);
        formData.append('nic', nic);
        formData.append('phone', phone);
        formData.append('profile', profile);
        formData.append('idCardFront', idCardFront);
        formData.append('idCardBack', idCardBack);
        formData.append('pricePerDay', pricePerDay);

        this.handleSaveGuide(formData)

    }
    handleSaveGuide(guide) {
        $.ajax({
            type: "POST",
            url: "http://localhost:8090/guide/api/v1/guide",
            data: guide,
            contentType: false,
            processData: false,
            success: (data) => {
                this.handleLoadAllGuide();
                this.reset();
            },
            error: (error) => {
                console.log(error)
            }
        })
    }
    handleLoadAllGuide() {
        $.ajax({
            type: "GET",
            url: "http://localhost:8090/guide/api/v1/guide/public",
            success: (response) => {
                this.handleLoadAllGuideCard(response);
            }
            ,
            error: (error) => {
                console.log(error)
            }
        })
    }
    handleLoadAllGuideCard(guides){
        $('#guide-body .row').empty();
        $('#guide-body .row').append(guides?.map(
            (guide) => this.renderGuideCard(guide)
        ).join(''));
    }
    renderGuideCard(guide){
        return `
            <div class="col-lg-3">
                <div class="guide_card">
                    <img class="guide_profile" src="data:image/jpeg;base64, ${guide.profile}" alt="guide profile img" style="width:100%">
                    <input id="guide_id" type="hidden" value="${guide.guideId}">
                    <div class="guide_name">${guide.name}</div>
                    <div class="guide_address">${guide.address}</div>
                    <div class="guide_phone">${guide.phone}</div>
                    <div class="guide_price"><small>LKR : </small>${guide.pricePerDay} per day</div>
                    <a  href="" data-bs-toggle="modal" data-bs-target="#guideDetailsModal" class="guide_more_about">view more about guide</a>
                    <button class="guide_book_now">Book Now</button>
                </div>
            </div>
        `;
    }
    reset() {
        $('#txtGuideId').val('');
        $('#txtGuideName').val('');
        $('#txtGuideAddress').val('');
        $('#txtGuideNic').val('');
        $('#txtGuidePhone').val('');
        $('#txtGuideProfile').val('');
        $('#txtGuideIdCardFront').val('');
        $('#txtGuideIdCardBack').val('');
        $('#txtGuidePricePerDay').val('');
    }
}
new GuideController();