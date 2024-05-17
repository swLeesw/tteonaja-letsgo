<script setup>
import { QuillEditor} from '@vueup/vue-quill';
import '@vueup/vue-quill/dist/vue-quill.snow.css';
import { ref, watch } from 'vue';

const data = ref("")

watch(data, (newValue, oldValue) => {
    // console.log(data.value.ops[0].insert)
    console.log(data)
})
</script>

<template>
    <div class="container text-center mt-5 shadow p-4 rounded">
        <h4 class="fw-bolder">자유게시판</h4>
        <div class="row align-self-center mt-4 p-1">
            <QuillEditor theme="snow" v-model:content="data" contentType="html" />
        </div>
        <div class="container" v-html="data">
        </div>

        <!-- <div class="container mt-2">
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
        </div> -->
        <div class="mt-3 d-flex justify-content-between align-items-center">
                <button class="btn btn-secondary btn-sm" @click="moveToList">글 목록</button>
                <button class="btn btn-primary btn-sm" @click="writeArticle">등록하기</button>
        </div>
    </div>
</template>

<style scoped>

</style>