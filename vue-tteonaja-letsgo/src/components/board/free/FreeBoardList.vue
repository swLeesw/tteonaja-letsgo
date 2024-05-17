<script setup>
import { useRouter, useRoute } from 'vue-router';
import { onMounted, ref } from 'vue';
import { listArticle } from '@/api/board.js';
import FreeBoardListItem from './item/FreeBoardListItem.vue';
import VPageNavigation from '@/components/common/VPageNavigation.vue';
import VSelect from '@/components/common/VSelect.vue';

const articles = ref([]);
const currentPage = ref(1);
const totalPage = ref(0);
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
            articles.value = response.data.articles;
            currentPage.value = response.data.currentPage;
            totalPage.value = response.data.totalPageCount;
        },
        (error) => {
            console.error(error);
        }
    );
};

const onPageChange = (val) => {
    currentPage.value = val;
    params.value.pgno = val;
    getArticleList();
}

onMounted(() => {
    getArticleList();
})

</script>

<template>
    <div>
        <div class="container text-center mt-5 shadow p-4 rounded">
            <h4 class="fw-bolder">자유게시판</h4>
            <div class="row align-self-center mt-4">
                <div class="col-md-5 offset-7">
                    <form class="d-flex">
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
                        <thead class="table-dark shadow">
                            <tr>
                                <th scope="col">게시글 번호</th>
                                <th scope="col">제목</th>
                                <th scope="col">작성자</th>
                                <th scope="col">작성날짜</th>
                                <th scope="col">조회수</th>
                            </tr>
                        </thead>
                        <tbody>
                            <FreeBoardListItem v-for="(article, index) in articles" :key="article.articleNo"
                                :elem="{ article: article, index: index }">
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

<style scoped></style>