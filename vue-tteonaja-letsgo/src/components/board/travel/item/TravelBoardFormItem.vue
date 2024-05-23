<script setup>
import { ref, watch, onMounted } from 'vue';
import { useRouter, useRoute } from 'vue-router';
import { getArticle, modifyArticle, registArticle } from '@/api/board';

import { jwtDecode } from 'jwt-decode';
import { KakaoMap, KakaoMapMarker, KakaoMapCustomOverlay } from 'vue3-kakao-maps';
import Swal from 'sweetalert2';
import { getAttraction, getSido, getGugun } from '@/api/attractionInfo.js';
import { QuillEditor } from '@vueup/vue-quill';
import '@vueup/vue-quill/dist/vue-quill.snow.css';

import ArrayControl from 'lodash';

const props = defineProps({ type: String });
const router = useRouter();
const route = useRoute();

const article = ref(
    {
        userId: "",
        subject: "",
        content: "",
        travelList: "",
    }
);

const attractionSelectedList = ref([]);
const attractionCustomSelectedList = ref([]);

// 글 작성 내용 감시
watch(
    () => { article.value.content; },
    (newValue, oldValue) => {
        console.log(article.value.content);
    },
    { deep: true }
);


// 글 수정시 원래 있던 제목, 내용 긁어옴
if (props.type === "modify") {
    article.value.articleNo = route.params.id;
    getArticle(
        {
            boardType: 'travel',
            articleNo: article.value.articleNo
        },
        (response) => {
            article.value.subject = response.data.subject;
            article.value.content = response.data.content;
        },
        (error) => {
            console.error(error);
        }
    );

    attractionSelectedList.value = JSON.parse(route.query.courseList)
    for (const elem of attractionSelectedList.value) {
        attractionCustomSelectedList.value.push({
            id: elem.id,
            sidoCode: elem.sidoCode,
            gugunCode: elem.gugunCode,
            contentCode: elem.typeId,
            firstImage: elem.firstImage
        })
    }
}

const moveToList = () => {
    router.replace({ name: 'travel-list' });
};

const controlArticle = async () => {
    let token = sessionStorage.getItem("accessToken");
    let decodeToken = jwtDecode(token);
    article.value.userId = decodeToken.userId;

    const params = {
        boardType: 'travel',
        article: {
            userId: article.value.userId,
            subject: article.value.subject,
            content: article.value.content,
            travelList: JSON.stringify(attractionCustomSelectedList.value)
        }
    };

    if (props.type === "regist") {
        await registArticle(
            params,
            (response) => {
                if (response.status == 201) {
                    Swal.fire({
                        position: "top",
                        icon: "success",
                        title: "글 등록이 완료되었습니다!",
                        showConfirmButton: false,
                        timer: 1500
                    });
                    moveToList();
                } else {
                    Swal.fire({
                        position: "top",
                        icon: "error",
                        title: "글 등록 처리에 문제가 발생했습니다!",
                        showConfirmButton: false,
                        timer: 1500
                    });
                }
            },
            (error) => console.error(error)
        );
    }

    if (props.type === "modify") {
        await modifyArticle(
            {
                boardType: 'travel',
                article: {
                    articleNo: article.value.articleNo,
                    subject: article.value.subject,
                    content: article.value.content,
                    travelList: JSON.stringify(attractionCustomSelectedList.value)
                }
            },
            (response) => {
                console.log(response);
                if (response.status == 200) {
                    Swal.fire({
                        position: "top",
                        icon: "success",
                        title: "글 수정이 완료되었습니다!",
                        showConfirmButton: false,
                        timer: 1500
                    });
                    moveToList();
                } else {
                    Swal.fire({
                        position: "top",
                        icon: "error",
                        title: "글 수정 처리에 문제가 발생했습니다!",
                        showConfirmButton: false,
                        timer: 1500
                    });
                }
            },
            (error) => {
                console.log(error)
                Swal.fire({
                    position: "top",
                    icon: "error",
                    title: "글 수정 처리에 문제가 발생했습니다!",
                    showConfirmButton: false,
                    timer: 1500
                });
            }
        );
    }
};







// --------------------------------------------------
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

//kakaoMapCustomOVeray


const getContent = (infoName, infoAddr1, infoHomepage) => {
    let tmp = ` <div
        style="
          padding: 10px;
          background-color: white;
          border: 1px solid #ccc;
          border-radius: 5px;
          display: flex;
          flex-direction: column;
          align-items: flex-start;
        "
      >
        <div style="font-weight: bold; margin-bottom: 5px">` + infoName + `</div>
        <div style="display: flex">
          <div style="margin-right: 10px">
            <img src="https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/thumnail.png" width="73" height="70" />
          </div>
          <div style="display: flex; flex-direction: column; align-items: flex-start">
            <div style="overflow: hidden; text-overflow: ellipsis; white-space: nowrap">` + infoAddr1 + `</div>`;

    if (infoHomepage != null) {
        tmp += infoHomepage + `
        
          </div>
        </div>
      </div>`;
    } else {
        tmp += `
          </div>
        </div>
      </div>`;
    }

    return tmp;
};

//db에서 여행지 정보 뽑아오기
const getAttractionf = () => (

    getAttraction(
        {
            sidoCode: sido.value,
            gugunCode: gugun.value,
            contentCode: contentCode.value,
            searchTerm: searchTerm.value,
        },
        (response) => {
            console.log(response.data);
            if (response.data == "" || response.data == null) {
                return;
            }
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
});

//db에서 sido 얻어오기
const getSidof = () => getSido(
    (response) => {
        sidoOptions.value = response.data;
    },
    (error) => {
        console.log(error);
    }
);


//db에서 gugun 얻어오기(sido의 값이 변경되면 실행된다.)
const getGugunf = watch(() => {

    if (sido.value == "") {
        gugunOptions.value = null;
        return;
    }

    getGugun(sido.value,
        (response) => {
            gugunOptions.value = response.data;
        },
        (error) => {
            console.log(error);
        }
    );
});


const onClickMapMarker = (info) => {
    coordinate.value.lat = info.latitude;
    coordinate.value.lng = info.longitude;
    if (info.visible == true) {
        info.visible = false;
    } else {
        for (let i = 0; i < attractionInfo.value.length; i++) {
            attractionInfo.value[i].visible = false;
        }
        info.visible = !info.visible;
    }



};



const addAttreaction = (info) => {
    if (!attractionSelectedList.value.includes(info)) {
        const attractionCustom = {
            id: info.id,
            sidoCode: info.sidoCode,
            gugunCode: info.gugunCode,
            contentCode: info.typeId,
            firstImage: info.firstImage
        };
        attractionSelectedList.value.push(info);
        attractionCustomSelectedList.value.push(attractionCustom);
        console.log(attractionCustomSelectedList.value);
    } else {

    }
};

const removeAttraction = (id) => {
    attractionSelectedList.value = ArrayControl.remove(attractionSelectedList.value, (info) => {
        return info.id !== id;
    });
    attractionCustomSelectedList.value = ArrayControl.remove(attractionCustomSelectedList.value, (info) => {
        return info.id !== id;
    });
};
//화면 로드가 되면 자동으로 sido 얻기
onMounted(() => {
    getSidof();

    // const mapDiv = document.querySelector(".map")
    // const sideBar = document.querySelector(".side-custom")
    // if (mapDiv && sideBar) {
    //     sideBar.style.height = mapDiv.offsetHeight
    // }

    // //아이디 불러우기
    // let token = sessionStorage.getItem("accessToken");

    // if (token == null) {
    //     return;
    // }

    // let decodeToken = jwtDecode(token);
    // userId.value = decodeToken.userId;

})

</script>

<template>
    <p class="text-secondary">여행 코스 만들기</p>
    <hr class="mb-5">
    <label for="attractionFormControlInput" class="form-label fw-bolder">여행지 검색</label>
    <section>
        <div class="vh-60 rounded mb-5" style="display: flex;">
            <!--맵 서치-->
            <div class="d-flex flex-column flex-shrink-0 bg-body-tertiary side-custom"
                style="width: 400px; z-index: 3; height: 60vh">
                <form @submit.prevent="getAttractionf" class="col-fluid align-items-center mt-2">
                    <input class="fc" type="text" v-model="searchTerm" placeholder="검색어 입력.." />
                    <button type="submit" class="btn btn-custom">검색</button>
                    <div class="ms-4">옵션</div>
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
                    <select class="col fc-option mx-4 mt-1 mb-3" aria-label="Default select example"
                        v-model="contentCode">
                        <option value="">콘텐츠 유형</option>
                        <option v-for="(contentt, index) in contentCodeOptions" :key="index" :value="contentt.value">
                            {{ contentt.name }}
                        </option>
                    </select>
                </form>
                <div class="list-group list-group-flush border-bottom scrollarea scroll-custom">
                    <div v-for="(info, index) in attractionInfo" :key="index">
                        <div class="d-flex ms-3 me-3 mt-4 mb-4 cardd" @click="onClickMapMarker(info)">
                            <img :src="info.firstImage" style="border-radius: 10px;" alt="사진" width="120px"
                                v-show="info.firstImage != ''">
                            <img src="@/assets/logo.png" alt="사진" width="120px" v-show="info.firstImage == ''">
                            <span class="ms-3 border border-1 opacity-90"></span>
                            <div class="card-content ms-3">
                                <h5 class="card-title mb-1">{{ info.name }}</h5>
                                <p class="card-description">{{ info.addr1 }}</p>
                                <button class="btn btn-custom me-3" aria-controls="offcanvasScrolling"
                                    @click.stop="addAttreaction(info)">여행지 추가</button>
                            </div>
                        </div>
                        <!-- data-bs-target="#offcanvasScrolling"
                                data-bs-toggle="offcanvas" -->
                    </div>
                </div>
            </div>
            <!--카카오 맵 시작-->
            <KakaoMap :lat="coordinate.lat" :lng="coordinate.lng" style="width: 100%; height: 60vh;">
                <KakaoMapMarker v-for="(info, idx) in attractionInfo " :key="idx" :lat="info.latitude"
                    :lng="info.longitude" :clickable="true" @onClickKakaoMapMarker="onClickMapMarker(info)" />

                <KakaoMapCustomOverlay v-for="(info, idx) in attractionInfo " :key="idx" :lat="info.latitude"
                    :lng="info.longitude" :content="getContent(info.name, info.addr1, info.homepage)"
                    :visible="info.visible" :yAnchor="1.4" />
            </KakaoMap>

            <!-- 
        <KakaoMapMarker :lat="33.450701" :lng="126.570667" @onClickKakaoMapMarker="onClickKakaoMapMarker"
            :clickable="true" />
        <KakaoMapCustomOverlay :lat="33.450701" :lng="126.570667" :yAnchor="1.4" :visible="visible"
            :content="content" /> -->

        </div>
    </section>

    <label for="myAttractionFormControlInput" class="form-label fw-bolder">선택한 여행지</label>
    <div class="overflow-x-auto border mb-5 ps-2">
        <!-- <div class="container-fluid border mb-5 ps-2"> -->
        <div class="d-inline-flex" v-for="(info, index) in attractionSelectedList" :key="index">
            <div class="d-inline-flex ms-3 me-3 mt-4 mb-4 cardd p-3" @click="onClickMapMarker(info)">
                <img :src="info.firstImage" style="border-radius: 10px;" alt="사진" width="120px"
                    v-show="info.firstImage != ''">
                <img src="@/assets/logo.png" alt="사진" width="120px" v-show="info.firstImage == ''">
                <span class="ms-3 border border-1 opacity-90"></span>
                <div class="card-content ms-3">
                    <h5 class="card-title mb-1">{{ info.name }}</h5>
                    <p class="card-description">{{ info.addr1 }}</p>
                    <button class="btn btn-custom-del me-3" aria-controls="offcanvasScrolling"
                        @click.stop="removeAttraction(info.id)">제거</button>
                </div>
            </div>
        </div>
    </div>

    <label for="subjectFormControlInput1" class="form-label fw-bolder">제목</label>
    <input type="email" class="subject-input mb-5" id="subjectFormControlInput1" v-model="article.subject"
        placeholder="글 제목을 입력하세요.">
    <label for="contentFormControlInput1" class="form-label fw-bolder">내용</label>
    <QuillEditor theme="snow" v-model:content="article.content" contentType="html" toolbar="full" />
    <!-- <div class="container" v-html="article.content">
    </div> -->
    <div class="mt-3 d-flex justify-content-between align-items-center">
        <button class="btn btn-secondary btn-sm" @click="moveToList">글 목록</button>
        <button class="btn btn-primary btn-sm" v-if="type === 'regist'" @click="controlArticle">등록하기</button>
        <button class="btn btn-primary btn-sm" v-if="type === 'modify'" @click="controlArticle">글 수정</button>
    </div>
</template>

<style scoped>
.subject-input {
    width: 100%;
    /* Make the title input width 100% */
    border-radius: 0;
    /* Remove border radius to make corners sharp */
    padding: 0.375rem 0.75rem;
    /* Add some padding to the input for better appearance */
    border: 1px solid #ced4da;
    /* Match the border style of typical form inputs */
    outline: none;
    /* Remove the default outline */
    box-sizing: border-box;
    /* Ensure the width includes padding and border */
}


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


/* 가로 스크롤 */
.overflow-x-auto {
    white-space: nowrap;
}

.mycard:hover {
    transform: scale(1.05);
    background-color: rgba(67, 180, 255, 0.158);
    border-radius: 10px;
    display: inline-block;
}

/* ----- */


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

.cardd {
    transition: all 0.2s linear;
    padding: 3px;
}

.cardd:hover {
    transition: all 0.2s linear;
    transform: scale(1.05);
    /* background-color: rgba(67, 180, 255, 0.09); */
    border-radius: 10px;
    box-shadow: 1px 3px 10px #8886861c;
}


.side-custom {
    box-shadow: 7px 0px 3px -5px #888686;
    z-index: 2;
}

.scroll-custom {
    overflow-y: auto;
    overscroll-behavior-y: contain;
    scrollbar-width: thin;
    scrollbar-color: rgba(67, 180, 255, 0.158) var(--bs-tertiary-bg);

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


.btn-custom:hover {
    background-color: rgba(67, 180, 255, 0.623);
    color: #888686;
}

.btn-custom:active {
    background-color: rgba(67, 180, 255, 0.027);
    color: #888686;
}

.btn-custom-del {
    background-color: rgba(255, 67, 67, 0.158);
    color: #888686;
    border: 1px solid;
    border-color: #8886861a;
}

.btn-custom-del:hover {
    background-color: rgba(255, 67, 67, 0.623);
    color: #888686;
}

.offcanvas-custom {
    --bs-offcanvas-width: 350px;
    --bs-offcanvas-bg: var(--bs-tertiary-bg);
    top: 95px;
    bottom: 70px;
    left: 410px;
    border-radius: 10px;
    z-index: 2;

}

.container {
    overflow-y: auto;
    scrollbar-width: thin;
    scrollbar-color: rgba(67, 180, 255, 0.158) var(--bs-tertiary-bg);
}
</style>