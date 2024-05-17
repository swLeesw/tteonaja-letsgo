<script setup>
import { onMounted, ref } from 'vue';
import { useRoute } from 'vue-router';
import { getArticle } from '@/api/board';

const route = useRoute();

const params = ref(
    {
        boardType: 'free',
        articleNo: route.params.id
    }
);

const article = ref({});

const getArticleDetail = () => {
    getArticle(params.value,
        (response) => {
            article.value = response.data;
            console.log(response);
        },
        (error) => {
            console.log("게시물 상세 정보 오류");
            console.log(error);
        }
    );
};

onMounted(() => {
    getArticleDetail();
});
</script>

<template>
    <div class="container text-center mt-5 shadow p-4 rounded">
        <h4 class="fw-bolder">자유게시판</h4>
        <div class="container mt-5">
            <div class="row justify-content-center">
                <table class="table">
                    <thead>
                        <tr>
                            <th colspan="9" class="table-dark">{{ article.subject }}</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <th colspan="1" class="table-secondary">작성자 </th>
                            <th colspan="2">{{ article.userId }}</th>
                            <th colspan="1" class="table-secondary">작성일자 </th>
                            <th colspan="2">{{ article.registerTime }}</th>
                            <th colspan="1" class="table-secondary">조회수 </th>
                            <th colspan="2">{{ article.hit }}</th>
                        </tr>
                        <tr>
                            <td colspan="9">{{ article.content }}</td>
                        </tr>
                    </tbody>
                </table>
            </div>
        </div>

        <div class="container mt-3 d-flex justify-content-between align-items-center p-1">
            <div>
                <button class="btn btn-secondary btn-sm" @click="moveToList">글 목록</button>
            </div>
            <div>
                <button class="btn btn-primary btn-sm" @click="moveToUpdate">글 수정</button>
                <button class="btn btn-danger ms-3 btn-sm" @click="deleteFreeArticle">글 삭제</button>
            </div>
        </div>
    </div>


</template>

<style scoped></style>