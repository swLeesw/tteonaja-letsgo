<script setup>
import { RouterLink } from 'vue-router';
import { useRouter, useRoute } from 'vue-router';
import { onMounted, ref } from 'vue';
import { listArticle } from '@/api/board.js';
import TravelBoardListItem from './item/TravelBoardListItem.vue';
import VPageNavigation from '@/components/common/VPageNavigation.vue';
import VSelect from '@/components/common/VSelect.vue';


const router = useRouter();
const articles = ref([]);
const thumbnails = ref([]);
const currentPage = ref(1);
const totalPage = ref(0);
const totalArticles = ref(-1);
const { VITE_TRAVEL_ARTICLE_LIST_SIZE } = import.meta.env;

const params = ref({
    boardType: 'travel',
    pgno: currentPage.value,
    spp: VITE_TRAVEL_ARTICLE_LIST_SIZE,
    key: "",
    word: "",
});

const selectOption = ref([
    { text: "검색조건", value: "" },
    { text: "게시글번호", value: "article_no" },
    { text: "제목", value: "subject" },
    { text: "작성자아이디", value: "user_id" },
]);

const getArticleList = () => {
    listArticle(
        params.value,
        (response) => {
            console.log(response.data.articles);
            console.log(response);
            articles.value = response.data.articles;
            currentPage.value = response.data.currentPage;
            totalPage.value = response.data.totalPageCount;
            if (totalArticles.value == -1) {
                setBoardNum();
            }
        },
        (error) => {
            console.error(error);
        }
    );
};

const setBoardNum = () => {
    listArticle(
        {
            boardType: 'free',
            pgno: totalPage.value,
            spp: VITE_TRAVEL_ARTICLE_LIST_SIZE
        },
        (response) => {
            totalArticles.value = VITE_TRAVEL_ARTICLE_LIST_SIZE * (totalPage.value - 1) + response.data.articles.length;
            console.log(totalArticles.value);
        },
        (error) => {
            console.log(error);
        }
    );
};

const moveToWrite = () => {
    router.push({ name: 'travel-write' });
};

const onPageChange = (val) => {
    currentPage.value = val;
    params.value.pgno = val;
    getArticleList();
};

const changeKey = (key) => {
    params.value.key = key;
};

onMounted(() => {
    getArticleList();
})


</script>

<template>
    <section class="py-5 text-center background-section">
        <div class="row py-lg-5">
            <div class="col-lg-6 col-md-8 mx-auto">
                <h1 class="fw-light mb-5">여행 코스</h1>
                <p class="lead text-muted mb-5">나만의 여행 코스를 설계하고, 소중한 경험을 다른 여행자들과 공유하세요. 다양한 팁과 추천 코스를 참고하여 완벽한 여행을
                    계획해보세요.</p>
                <p>
                    <RouterLink :to="{ name: 'travel-write' }" class="btn btn-primary">나만의 여행 코스 작성</RouterLink>
                </p>
            </div>
        </div>
    </section>

    <div class="album py-5 bg-light vh=100">
        <div class="container">

            <div class="row row-cols-1 row-cols-sm-2 row-cols-md-3 g-3 mb-5">
                <TravelBoardListItem v-for="article in articles" :key="article.id" :elem="article">
                </TravelBoardListItem>
            </div>

            <div class="row mt-5">
                <div class="col text-center">
                    <VPageNavigation :current-page="currentPage" :total-page="totalPage" @pageChange="onPageChange">
                    </VPageNavigation>
                </div>
            </div>
        </div>


    </div>


</template>

<style scoped>
/* .background-section {
    background-image: url('@/assets/course.jpg');
    background-size: cover;
    background-position: center;
    background-repeat: no-repeat;
} */

.background-section {
    position: relative;
    overflow: hidden;
}

.background-section::before {
    content: '';
    position: absolute;
    top: 0;
    left: 0;
    width: 100%;
    height: 100%;
    background-image: url('@/assets/course.jpg');
    background-size: cover;
    background-position: center;
    filter: blur(3px);
    /* 흐림 효과 적용 */
    z-index: -1;
    /* 배경을 뒤로 보내기 */
}

.background-section .content {
    position: relative;
    z-index: 1;
    /* 콘텐츠를 앞쪽에 배치 */
}
</style>