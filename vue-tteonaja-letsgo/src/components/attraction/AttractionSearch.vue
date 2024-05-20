<script setup>
import { ref, onMounted, watch } from 'vue';
//import router
import { useRouter } from "vue-router";
// import axios from 'axios'
import { KakaoMap, KakaoMapMarker } from 'vue3-kakao-maps';
import { getAttraction, getSido, getGugun } from '@/api/attractionInfo.js';

//router
const router = useRouter();

//options에서 선택된 장소
const sido = ref("");
const gugun = ref("");
const contentCode = ref("");
const searchTerm = ref("");
//전체 정보
const attractionInfo = ref("");

//옵션(db에서 뽑아온 전체 sido, gugun 정보 모음(content type만 따로 생성))
const sidoOptions = ref([]);
const gugunOptions = ref([]);
const contentCodeOptions = ref([
    {
        name: "관광지",
        value: 12,
    },
    {
        name: "문화시설",
        value: 14
    },
    {
        name: "축제/공연/행사",
        value: 15,
    },
    {
        name: "여행코스",
        value: 25
    },
    {
        name: "레포츠",
        value: 28
    },
    {
        name: "숙박",
        value: 32
    },
    {
        name: "쇼핑",
        value: 38
    },
    {
        name: "음식",
        value: 39
    },
]);

//리뷰보기
const moveReview = (value) => {

    console.log('movie.value=' + value);
    router.push({ name: "map-review", params: { id: value } });
}

//db에서 여행지 정보 뽑아오기
const getAttractionf = () => (getAttraction(
    {
        sidoCode: sido.value,
        gugunCode: gugun.value,
        contentCode: contentCode.value,
        searchTerm: searchTerm.value,
    },
    (response) => {
        console.log(response.data);
        attractionInfo.value = response.data;
        attractionInfo.value.forEach(info => info.visible = false);
        coordinate.value.lat = attractionInfo.value[0].latitude;
        coordinate.value.lng = attractionInfo.value[0].longitude;
    },
    (error) => {
        console.log(error);
    }
));


//지도화면 좌표
const coordinate = ref({
    lat: 37.566826,
    lng: 126.9786567
})

//db에서 sido 얻어오기
const getSidof = () => getSido(
    (response) => {
        sidoOptions.value = response.data;
    },
    (error) => {
        console.log(error);
    }
)


//db에서 gugun 얻어오기(sido의 값이 변경되면 실행된다.)
const getGugunf = watch(() => {

    if (sido.value == "") return;

    getGugun(sido.value,
        (response) => {
            gugunOptions.value = response.data;
        },
        (error) => {
            console.log(error);
        }
    )
})


const onClickMapMarker = (info) => {

    if (info.visible == true) {
        info.visible = false;
    } else {
        for (let i = 0; i < attractionInfo.value.length; i++) {
            attractionInfo.value[i].visible = false;
        }
        info.visible = !info.visible;
    }



}


//화면 로드가 되면 자동으로 sido 얻기
onMounted(() => {
    getSidof();
})

/* global bootstrap: false */
// (() => {
//     'use strict'
//     const tooltipTriggerList = Array.from(document.querySelectorAll('[data-bs-toggle="tooltip"]'))
//     tooltipTriggerList.forEach(tooltipTriggerEl => {
//         new bootstrap.Tooltip(tooltipTriggerEl)
//     })
// })()


</script>

<template>
    <div class="vh-100" style="display: flex;">
        <!--맵 서치-->
        <div class="d-flex flex-column flex-shrink-0 bg-body-tertiary sdw" style="width: 380px;">
            <form @submit.prevent="getAttractionf" class="col-fluid align-items-center mt-2">
                <input class="fc" type="text" v-model="searchTerm" placeholder="검색어 입력.." />
                <button type="submit" class="btn btn-custom">검색</button>
                <div class="ms-4" style="color=">옵션</div>
                <select class="col fc-option mx-4" aria-label="Default select example" v-model="sido">
                    <option value="">시/도</option>
                    <option v-for="(sidoo, index) in sidoOptions" :key="index" :value="sidoo.sidoCode">
                        {{ sidoo.sidoName }}
                    </option>
                </select>
                <select class="col fc-option mx-4 mt-1" aria-label="Default select example" v-model="gugun">
                    <option value="">구/군</option>
                    <option v-for="(gugunn, index) in gugunOptions" :key="index" :value="gugunn.gugunCode">
                        {{ gugunn.gugunName }}</option>
                </select>
                <select class="col fc-option mx-4 mt-1 mb-3" aria-label="Default select example" v-model="contentCode">
                    <option value="">콘텐츠 유형</option>
                    <option v-for="(contentt, index) in contentCodeOptions" :key="index" :value="contentt.value">
                        {{ contentt.name }}
                    </option>
                </select>
            </form>
            <div class="list-group list-group-flush border-bottom scrollarea">
                <div v-for="(info, index) in attractionInfo" :key="index">
                    <div class="d-flex ms-3 me-3 mt-4 mb-4 cardd" @click="onClickMapMarker(info)">
                        <img :src="info.firstImage" style="border-radius: 10px;" alt="사진" width="120px"
                            v-show="info.firstImage != ''">
                        <img src="@/assets/logo.png" alt="사진" width="120px" v-show="info.firstImage == ''">
                        <span class="ms-3 border border-1 opacity-90"></span>
                        <div class="card-content ms-3">
                            <h5 class="card-title mb-1">{{ info.name }}</h5>
                            <p class="card-description">{{ info.addr1 }}</p>
                            <button class="btn btn-custom me-3" data-bs-toggle="offcanvas"
                                data-bs-target="#offcanvasScrolling" aria-controls="offcanvasScrolling"
                                @click="moveReview(info.id)">{{ info.id }}</button>
                            <button class="btn btn-custom" @click.stop="console.log('좋아요')">좋아요</button>
                        </div>
                    </div>

                </div>
            </div>
        </div>
        <!--리뷰 오프캔버스-->
        <div class="offcanvas offcanvas-end " data-bs-scroll="true" data-bs-backdrop="false" tabindex="-1"
            id="offcanvasScrolling" aria-labelledby="offcanvasScrollingLabel" style="top: 95px; border-radius: 10px;">
            <!-- <div class="offcanvas-header">
                <h5 class="offcanvas-title" id="offcanvasScrollingLabel">리뷰</h5>
                <button type="button" class="btn-close" data-bs-dismiss="offcanvas" aria-label="Close"></button>
            </div>
            <div class="offcanvas-body">
                <p>Try scrolling the rest of the page to see this option in action.</p>
            </div> -->
            <router-view />
        </div>
        <!--리뷰 오프캔버스 끝-->
        <!--카카오 맵 시작-->
        <KakaoMap :lat="coordinate.lat" :lng="coordinate.lng" style="width: 100%; height: 100vh;">
            <KakaoMapMarker v-for="(info, idx) in attractionInfo " :key="idx" :lat="info.latitude" :lng="info.longitude"
                :infoWindow="{
                content:
                    `
                    <div>
                        ` + info.name + `
                    </div>
                    <div>
                        ` + info.overview + `
                    </div>
                        `
                , visible: info.visible
            }" :clickable="true" @onClickKakaoMapMarker="onClickMapMarker(info)" />
        </KakaoMap>
    </div>

</template>

<style scoped>
body {
    min-height: 100vh;
    min-height: -webkit-fill-available;
}

html {
    height: -webkit-fill-available;
}

main {
    height: 100vh;
    height: -webkit-fill-available;
    max-height: 100vh;
    overflow-x: auto;
    overflow-y: hidden;
}

.dropdown-toggle {
    outline: 0;
}

.btn-toggle {
    padding: .25rem .5rem;
    font-weight: 600;
    color: var(--bs-emphasis-color);
    background-color: transparent;
}

.card-custom:hover,
.card-custom:focus {
    color: rgba(var(--bs-emphasis-color-rgb), .85);
    background-color: var(--bs-tertiary-bg);
}

.btn-toggle:hover,
.btn-toggle:focus {
    color: rgba(var(--bs-emphasis-color-rgb), .85);
    background-color: var(--bs-tertiary-bg);
}

.btn-toggle::before {
    width: 1.25em;
    line-height: 0;
    content: url("data:image/svg+xml,%3csvg xmlns='http://www.w3.org/2000/svg' width='16' height='16' viewBox='0 0 16 16'%3e%3cpath fill='none' stroke='rgba%280,0,0,.5%29' stroke-linecap='round' stroke-linejoin='round' stroke-width='2' d='M5 14l6-6-6-6'/%3e%3c/svg%3e");
    transition: transform .35s ease;
    transform-origin: .5em 50%;
}

[data-bs-theme="dark"] .btn-toggle::before {
    content: url("data:image/svg+xml,%3csvg xmlns='http://www.w3.org/2000/svg' width='16' height='16' viewBox='0 0 16 16'%3e%3cpath fill='none' stroke='rgba%28255,255,255,.5%29' stroke-linecap='round' stroke-linejoin='round' stroke-width='2' d='M5 14l6-6-6-6'/%3e%3c/svg%3e");
}

.btn-toggle[aria-expanded="true"] {
    color: rgba(var(--bs-emphasis-color-rgb), .85);
}

.btn-toggle[aria-expanded="true"]::before {
    transform: rotate(90deg);
}

.btn-toggle-nav a {
    padding: .1875rem .5rem;
    margin-top: .125rem;
    margin-left: 1.25rem;
}

.btn-toggle-nav a:hover,
.btn-toggle-nav a:focus {
    background-color: var(--bs-tertiary-bg);
}

.scrollarea {
    overflow-y: auto;
}

.cardd:hover {
    transform: scale(1.05);
    background-color: rgba(67, 180, 255, 0.158);
    border-radius: 10px;
}

.sdw {
    box-shadow: 3px 0 10px #888686;
    z-index: 2;
}

.fc {
    padding-top: 5px;
    padding-bottom: 8px;
    padding-left: 15px;
    padding-right: 30px;
    margin: 25px;
    border: 1px solid;
    border-color: hsla(202, 78%, 56%, 0.199);
    border-radius: 5px;
}

/* .fc:focus {
    border: 1px solid;
    border-color: hsla(202, 78%, 56%, 0.199);
    border-radius: 5px;
} */

.fc-option {
    padding-top: 3px;
    padding-bottom: 3px;
    padding-left: 3px;
    padding-right: 3px;
    margin: 5px;
    border: 1px solid;
    border-color: hsla(202, 78%, 56%, 0.199);
    border-radius: 5px;
    color: #888686;
}

.btn-custom {
    background-color: rgba(67, 180, 255, 0.158);
    color: #888686;
    border: 1px solid;
    border-color: #8886861a;
}
</style>