<script setup>
import { useRouter, useRoute } from 'vue-router';
import { onMounted, ref } from 'vue';
import { listArticle } from '@/api/board.js';
import FreeBoardListItem from './item/FreeBoardListItem.vue';
import VPageNavigation from '@/components/common/VPageNavigation.vue';
import VSelect from '@/components/common/VSelect.vue';

const router = useRouter();
const articles = ref([]);
const currentPage = ref(1);
const totalPage = ref(0);
const totalArticles = ref(-1);
const { VITE_ARTICLE_LIST_SIZE } = import.meta.env;
const params = ref({
    boardType: 'free',
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
    router.push({ name: 'free-write' });
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
                <h1 class="fw-light mb-5">자유게시판</h1>
                <p class="lead text-dark fs-bolder">다양한 사람들과 자유롭게 정보를 공유하는 공간입니다.</p>
                <br><br><br><br>
            </div>
        </div>
    </section>
    <div class="text-center shadow p-4 rounded bg-light vh-100">
        <!-- <h4 class="fw-light fs-2">자유게시판</h4> -->
        <div class="container text-center mt-5 p-4 rounded vh-100">
            <div class="row align-self-center mt-4">
                <div class="col-md-5 offset-7">
                    <form @submit.prevent="getArticleList" class="d-flex">
                        <VSelect :selectOption="selectOption" @onKeySelect="changeKey" />
                        <div class="input-group input-group-sm ms-1">
                            <input type="text" class="form-control" v-model="params.word" placeholder="검색어..." />
                            <button class="btn btn-dark" type="button" @click="getArticleList">검색</button>
                        </div>
                    </form>
                </div>
            </div>
            <div class="container mt-2">
                <div class="row justify-content-center">
                    <table class="table">
                        <thead class="table-secondary shadow">
                            <tr>
                                <th scope="col">게시글 번호</th>
                                <th scope="col">제목</th>
                                <th scope="col">작성자</th>
                                <th scope="col">작성날짜</th>
                                <th scope="col">조회수</th>
                            </tr>
                        </thead>
                        <tbody>
                            <FreeBoardListItem v-for="(article, index) in articles" :key="article.articleNo" :elem="{
                                article: article,
                                total: totalArticles,
                                currentPage: currentPage,
                                index: index,
                                boardType: params.boardType
                            }">
                            </FreeBoardListItem>
                        </tbody>
                    </table>
                </div>
            </div>
            <div class="row">
                <div class="col offset-2 text-center">
                    <VPageNavigation :current-page="currentPage" :total-page="totalPage" @pageChange="onPageChange">
                    </VPageNavigation>
                </div>
                <div class="col-md-2 text-end">
                    <button class="btn btn-primary btn-sm" @click="moveToWrite">글 작성</button>
                </div>
            </div>
        </div>
    </div>
</template>

<style scoped>
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
    background-image: url('@/assets/free.jpg');
    background-size: cover;
    background-position: center;
    filter: blur(5px);
    min-height: 80vh;
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