<script setup>
import { onMounted, ref } from 'vue';
import { useRouter } from 'vue-router';
import { getTopAttraction } from "@/api/attractionInfo.js";
import { getTop } from "@/api/board.js";
const router = useRouter();

const attractionTop = ref([]);
// const reviewTop = ref([]);
const courseTop = ref([]);
const freeTop = ref([]);
const monthlyCourseImg = ref(null);
const monthlyFreeImg = ref(null);

const getAttractionTop = () => {
    getTopAttraction(
        ({ data }) => {
            attractionTop.value = data;
        },
        (error) => {
            console.log(error);
        }
    )
}

const getCourseTop = () => {
    getTop(
        { boardType: 'travel' },
        ({ data }) => {
            courseTop.value = data;
            let rand = Math.floor(Math.random() * (5 - 1 + 1)) + 1;
            monthlyCourseImg.value = `@/assets/randomImg/img${rand}.jpg`;
        },
        (error) => {
            console.log(error);
        }
    )

}

const getFreeTop = () => {
    getTop(
        { boardType: 'free' },
        ({ data }) => {
            freeTop.value = data;
        },
        (error) => {
            console.log(error);
        }
    )
}

const moveAttractionSearch = (attractionName) => {
    // console.log(attractionName);
    router.push({ name: "map", params: { attractionName: attractionName } })

}

const unfold = (event) => {

    const targetElement = event.target;
    targetElement.classList
    targetElement.classList.toggle('fold-controll');

}

onMounted(() => {
    getAttractionTop();
    getCourseTop();
    getFreeTop();
})

</script>

<template>
    <!-- <div class='container-fluid z-1 position-absolute pt-5 ps-5 ms-5 mt-5'>
        <p class="fs-3"><span class="text-dark fs-1 fw-bold">떠나자, Let's Go</span>에서</p>
        <p class="fs-3">대한민국에 숨겨진 관광지를 찾고</p>
        <p class="fs-3">여행을 함께 해요!</p>
        <div class='container-1-buttons'>
            <button class='btn btn-light' id="mapBtn">관광지 찾으러가기 ></button>
            <button class='btn btn-light ms-3'>후기 보러 가기 ></button>
        </div>
    </div>
    <div class="mt-0 container-fluid p-0 z-0 position-relative">
        <img src="@/assets/main.jpg" class="img-fluid">
    </div> -->

    <div id="myCarousel" class="carousel slide" data-bs-ride="carousel">
        <div class="carousel-indicators">
            <button type="button" data-bs-target="#myCarousel" data-bs-slide-to="0" class="active" aria-current="true"
                aria-label="Slide 1"></button>
            <button type="button" data-bs-target="#myCarousel" data-bs-slide-to="1" aria-label="Slide 2"></button>
            <button type="button" data-bs-target="#myCarousel" data-bs-slide-to="2" aria-label="Slide 3"></button>
        </div>
        <div class="carousel-inner">
            <div class="carousel-item active">
                <img src="@/assets/new_main.jpg" class="bd-placeholder-img" width="100%" height="100%"
                    aria-hidden="true" preserveAspectRatio="xMidYMid slice" focusable="false">
                <div class="container">
                    <div class="carousel-caption text-start ">
                        <h1 class="">떠나자, Let's Go</h1>
                        <p class="">에서 대한민국에 숨겨진 관광지를 찾고 여행을 함께 해요!</p>
                        <RouterLink class="btn btn-lg btn-warning " :to="{ name: 'map' }">관광지 검색</RouterLink>
                    </div>
                </div>
            </div>
            <!-- <div class="carousel-item">
                <svg class="bd-placeholder-img" width="100%" height="100%" xmlns="http://www.w3.org/2000/svg"
                    aria-hidden="true" preserveAspectRatio="xMidYMid slice" focusable="false">
                    <rect width="100%" height="100%" fill="#777" />
                </svg>

                <div class="container">
                    <div class="carousel-caption">
                        <h1>Another example headline.</h1>
                        <p>Some representative placeholder content for the second slide of the carousel.</p>
                        <p><a class="btn btn-lg btn-primary" href="#">Learn more</a></p>
                    </div>
                </div>
            </div>
            <div class="carousel-item">
                <svg class="bd-placeholder-img" width="100%" height="100%" xmlns="http://www.w3.org/2000/svg"
                    aria-hidden="true" preserveAspectRatio="xMidYMid slice" focusable="false">
                    <rect width="100%" height="100%" fill="#777" />
                </svg>

                <div class="container">
                    <div class="carousel-caption text-end">
                        <h1>One more for good measure.</h1>
                        <p>Some representative placeholder content for the third slide of this carousel.</p>
                        <p><a class="btn btn-lg btn-primary" href="#">Browse gallery</a></p>
                    </div>
                </div>
            </div> -->
        </div>
        <button class="carousel-control-prev" type="button" data-bs-target="#myCarousel" data-bs-slide="prev">
            <span class="carousel-control-prev-icon" aria-hidden="true"></span>
            <span class="visually-hidden">Previous</span>
        </button>
        <button class="carousel-control-next" type="button" data-bs-target="#myCarousel" data-bs-slide="next">
            <span class="carousel-control-next-icon" aria-hidden="true"></span>
            <span class="visually-hidden">Next</span>
        </button>
    </div>

    <div class="container marketing mt-5">

        <!-- Three columns of text below the carousel -->


        <!-- START THE FEATURETTES -->

        <hr class="featurette-divider">
        <!--관광지 탑 5-->
        <div class="text-center">
            <h2 class="featurette-heading fw-normal lh-1 mb-5 mt-5"> 이달의 관광지
                <p class="text-muted fs-5 mt-3">최근에 가장 인기있는 관광지를 방문하세요!</p>
            </h2>
            <div class="row d-flex justify-content-center">
                <div class="card-custom  rounded m-2 p-2 a col-lg-2 text-center mb-2"
                    v-for="(info, index) in attractionTop" :key="index">
                    <img v-show="info.firstImage != ''" :src="info.firstImage" class="bd-placeholder-img rounded-circle"
                        width="100" height="100" alt="">
                    <img v-show="info.firstImage == ''" src="@/assets/attractionAlter.png"
                        class="bd-placeholder-img rounded-circle" width="100" height="100" alt="">
                    <p class="fs-4 m-3 truncate text-font-weight-bold">{{
                        info.name }}</p>
                    <p v-show="info.overview != null" class="truncate" @click="unfold($event)" v-html="info.overview">
                    </p>
                    <p v-show="info.overview == null"><br><br></p>
                    <p style="color: gray;">{{ info.addr1 }}</p>
                    <button class="btn  btn-secondary" @click="moveAttractionSearch(info.name)">지도에서 보기</button>
                </div>
            </div>
        </div>
        <!--관광지 top 5 end-->
        <!--이달의 리뷰 top 5-->
        <hr class="featurette-divider mb-5 mt-5">

        <div class="row featurette">
            <div class="col-md-7 order-md-2">
                <h2 class="featurette-heading fw-normal lh-1">이달의 여행 코스</h2>
                <table class="table">
                    <thead>
                        <tr>
                            <th scope="col">게시글 번호</th>
                            <th scope="col">제목</th>
                            <th scope="col">작성자</th>
                            <th scope="col">작성 날짜</th>
                            <th scope="col">조회수</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr v-for="(course, index) in courseTop" :key="index">
                            <th scope="row">{{ course.articleNo }}</th>
                            <td>{{ course.subject }}</td>
                            <td>{{ course.userId }}</td>
                            <td>{{ course.registerTime }}</td>
                            <td>{{ course.hit }}</td>
                        </tr>
                    </tbody>
                </table>
            </div>
            <div class="col-md-5 order-md-1">
                <img class="rounded mt-3" src="@/assets/randomImg/img4.jpg" width="400px" alt="">
            </div>
        </div>
        <!--이달의 리뷰 top5 end-->

        <hr class="featurette-divider mb-5 mt-5">

        <div class="row featurette">
            <div class="col-md-7">
                <h2 class="featurette-heading fw-normal lh-1">인기글</h2>
                <table class="table">
                    <thead>
                        <tr>
                            <th scope="col">게시글 번호</th>
                            <th scope="col">제목</th>
                            <th scope="col">작성자</th>
                            <th scope="col">작성 날짜</th>
                            <th scope="col">조회수</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr v-for="(course, index) in freeTop" :key="index">
                            <th scope="row">{{ course.articleNo }}</th>
                            <td>{{ course.subject }}</td>
                            <td>{{ course.userId }}</td>
                            <td>{{ course.registerTime }}</td>
                            <td>{{ course.hit }}</td>
                        </tr>
                    </tbody>
                </table>
            </div>
            <div class="col-md-5">
                <img class="rounded mt-3 ms-5" src="@/assets/randomImg/img2.jpg" width="400px" alt="">

            </div>
        </div>

        <hr class="featurette-divider">

        <!-- /END THE FEATURETTES -->

    </div><!-- /.container -->
</template>

<style scoped>
.bd-placeholder-img {
    font-size: 1.125rem;
    text-anchor: middle;
    -webkit-user-select: none;
    -moz-user-select: none;
    user-select: none;
}

@media (min-width: 768px) {
    .bd-placeholder-img-lg {
        font-size: 3.5rem;
    }
}

.b-example-divider {
    height: 3rem;
    background-color: rgba(0, 0, 0, .1);
    border: solid rgba(0, 0, 0, .15);
    border-width: 1px 0;
    box-shadow: inset 0 .5em 1.5em rgba(0, 0, 0, .1), inset 0 .125em .5em rgba(0, 0, 0, .15);
}

.b-example-vr {
    flex-shrink: 0;
    width: 1.5rem;
    height: 100vh;
}

.bi {
    vertical-align: -.125em;
    fill: currentColor;
}

.truncate {
    display: -webkit-box;
    -webkit-box-orient: vertical;
    -webkit-line-clamp: 2;
    /* 최대 줄 수 설정 */
    /* white-space: nowrap; */
    /* 텍스트를 한 줄로 만듦 */
    overflow: hidden;
    /* 넘치는 텍스트를 숨김 */
    text-overflow: ellipsis;
    /* 넘치는 부분을 ...으로 표시 */
}

.fold-controll {
    -webkit-line-clamp: 3;
    overflow-y: auto;
    overscroll-behavior-y: contain;
    scrollbar-width: thin;
}

.card-custom {
    /* background-color: rgb(145, 233, 70); */
    background-color: rgba(235, 233, 233, 0.534);
}

.a img {
    transition: all 0.2s linear;
}

.a:hover img {
    transform: scale(1.12);
    opacity: 0.7;
}
</style>