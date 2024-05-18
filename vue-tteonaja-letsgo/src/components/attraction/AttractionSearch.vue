<script setup>
import { ref, onMounted, watch } from 'vue';
import axios from 'axios'
import { KakaoMap, KakaoMapMarker } from 'vue3-kakao-maps';


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


//db에서 여행지 정보 뽑아오기
const getAttraction = () => {
    axios.get('http://localhost/tteonaja/api/attractionInfo/region', {
        params: {
            sidoCode: sido.value,
            gugunCode: gugun.value,
            contentCode: contentCode.value,
        }
    })
        .then(response => {
            attractionInfo.value = response.data;
            attractionInfo.value.forEach(info => info.visible = false);
            coordinate.value.lat = attractionInfo.value[0].latitude;
            coordinate.value.lng = attractionInfo.value[0].longitude;
        })
        .catch(error => {
            console.log(error);
        })
}


//지도화면 좌표
const coordinate = ref({
    lat: 37.566826,
    lng: 126.9786567
})

//db에서 sido 얻어오기
const getSido = () => {
    axios.get('http://localhost/tteonaja/api/attractionInfo/sido')
        .then(response => {
            sidoOptions.value = response.data;
        })
        .catch(error => {
            console.log(error)
        })
}

//db에서 gugun 얻어오기(sido의 값이 변경되면 실행된다.)
const getGugun = watch(() => {

    if (sido.value == "") return; //sido의 값이 들어오지 않았다면 요청하지 않는다.

    axios.get('http://localhost/tteonaja/api/attractionInfo/gugun/' + sido.value)
        .then(response => {
            gugunOptions.value = response.data;
        })
        .catch(error => {
            console.log(error)
        })
})

const onClickMapMarker = (info) => {
    for (let i = 0; i < attractionInfo.value.length; i++) {
        attractionInfo.value[i].visible = false;
    }
    info.visible = !info.visible;


}


//화면 로드가 되면 자동으로 sido 얻기
onMounted(() => {
    getSido();
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
    <div class="container-fluid vh-100" style="display: flex;">
        <div class="d-flex flex-column align-items-stretch flex-shrink-0 bg-body-tertiary" style="width: 380px;">
            <a href="/"
                class="d-flex align-items-center flex-shrink-0 p-3 link-body-emphasis text-decoration-none border-bottom">
                <svg class="bi pe-none me-2" width="30" height="24">
                    <use xlink:href="#bootstrap" />
                </svg>
                <span class="fs-5 fw-semibold">검색</span>
            </a>
            <div class="list-group list-group-flush border-bottom scrollarea">
                <select class="col form-select mx-2" aria-label="Default select example" v-model="sido">
                    <option value="">시/도 선택</option>
                    <option v-for="(sidoo, index) in sidoOptions" :key="index" :value="sidoo.sidoCode">{{ sidoo.sidoName
                        }}
                    </option>
                </select>
                <select class="col form-select mx-2" aria-label="Default select example" v-model="gugun">
                    <option value="">구/군 선택</option>
                    <option v-for="(gugunn, index) in gugunOptions" :key="index" :value="gugunn.gugunCode">{{
                    gugunn.gugunName
                }}</option>
                </select>
                <select class="col form-select mx-2" aria-label="Default select example" v-model="contentCode">
                    <option value="">콘텐츠 유형 선택</option>
                    <option v-for="(contentt, index) in contentCodeOptions" :key="index" :value="contentt.value">{{
                    contentt.name }}</option>
                </select>
                <input class="form-control mx-2" type="text" v-model="searchTerm" placeholder="검색어 입력.." />
                <button type="button" class="btn btn-primary col-2 mx-2 mt-3 mb-3" @click="getAttraction">검색</button>
                <div class="" v-for="(info, index) in attractionInfo" :key="index">
                    <div class="card lh-sm m-2 card-custom" style="width: 18rem;">
                        <img :src="info.firstImage" class="card-img-top"
                            onerror="this.onerror=null; this.src='../../assets/logo.png'">
                        <div class="card-body">
                            <h5 class="card-title">{{ info.name }}</h5>
                            <p class="card-text">설명...</p>
                            <a href="#" class="btn btn-primary" @click="onClickMapMarker(info)">위치 보기</a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <KakaoMap :lat="coordinate.lat" :lng="coordinate.lng" style="width: 100%; height: 100vh;">
            <KakaoMapMarker v-for="(info, idx) in  attractionInfo " :key="idx" :lat="info.latitude"
                :lng="info.longitude" :infoWindow="{
                    content:
                        `
                    <div>
                        ` + info.name + `
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
</style>