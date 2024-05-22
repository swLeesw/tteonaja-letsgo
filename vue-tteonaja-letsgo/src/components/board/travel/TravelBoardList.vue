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
const thumbnails = ref([])
const currentPage = ref(1);
const totalPage = ref(0);
const totalArticles = ref(-1);
const { VITE_ARTICLE_LIST_SIZE } = import.meta.env;

const params = ref({
    boardType: 'travel',
    pgno: currentPage.value,
    spp: VITE_ARTICLE_LIST_SIZE,
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
            spp: VITE_ARTICLE_LIST_SIZE
        },
        (response) => {
            totalArticles.value = VITE_ARTICLE_LIST_SIZE * (totalPage.value - 1) + response.data.articles.length;
            console.log(totalArticles.value);
        },
        (error) => {
            console.log(error);
        }
    );
};

const moveToWrite = () => {
    router.push({ name: 'travel-write' });
}

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
    <section class="py-5 text-center container">
        <div class="row py-lg-5">
            <div class="col-lg-6 col-md-8 mx-auto">
                <h1 class="fw-light">여행 코스</h1>
                <p class="lead text-muted">Something short and leading about the collection below—its contents, the
                    creator, etc. Make it short and sweet, but not too short so folks don’t simply skip over it
                    entirely.</p>
                <p>
                    <RouterLink :to="{ name: 'travel-write' }" class="btn btn-primary">나만의 여행 코스 작성</RouterLink>
                </p>
            </div>
        </div>
    </section>

    <div class="album py-5 bg-light">
        <div class="container">

            <div class="row row-cols-1 row-cols-sm-2 row-cols-md-3 g-3">

                <div class="col">
                    <div class="card shadow-sm">
                        <svg class="bd-placeholder-img card-img-top" width="100%" height="225"
                            xmlns="http://www.w3.org/2000/svg" role="img" aria-label="Placeholder: Thumbnail"
                            preserveAspectRatio="xMidYMid slice" focusable="false">
                            <title>Placeholder</title>
                            <rect width="100%" height="100%" fill="#55595c" /><text x="50%" y="50%" fill="#eceeef"
                                dy=".3em">Thumbnail</text>
                        </svg>

                        <div class="card-body">
                            <p class="card-text">This is a wider card with supporting text below as a natural lead-in to
                                additional content. This content is a little bit longer.</p>
                            <div class="d-flex justify-content-between align-items-center">
                                <div class="btn-group">
                                    <button type="button" class="btn btn-sm btn-outline-secondary">View</button>
                                    <button type="button" class="btn btn-sm btn-outline-secondary">Edit</button>
                                </div>
                                <small class="text-muted">9 mins</small>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</template>

<style scoped></style>