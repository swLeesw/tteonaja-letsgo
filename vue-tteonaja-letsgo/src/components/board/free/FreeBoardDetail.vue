<script setup>
import { onMounted, ref } from 'vue';
import { useRoute, useRouter } from 'vue-router';
import { deleteArticle, getArticle } from '@/api/board';
import Swal from 'sweetalert2';

const route = useRoute();
const router = useRouter();

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

const deleteFreeArticle = () => {
    deleteArticle(
        params.value,
        (response) => {
            if (response.status == 200) {
                Swal.fire({
                    position: "top",
                    icon: "success",
                    title: "글 삭제가 완료되었습니다!",
                    showConfirmButton: false,
                    timer: 1500
                });
                moveToList();
            } else {
                Swal.fire({
                    position: "top",
                    icon: "error",
                    title: "글 삭제 처리에 문제가 발생했습니다!",
                    showConfirmButton: false,
                    timer: 1500
                });
            }
        },
        (error) => {
            Swal.fire({
                position: "top",
                icon: "error",
                title: "서버에 문제가 발생했습니다!",
                showConfirmButton: false,
                timer: 1500
            });
            console.log(error)
        }
    )
}

onMounted(() => {
    getArticleDetail();
});

const moveToList = () => {
    router.replace({ name: 'free-list' });
};

const moveToModify = () => {
    router.push({ name: 'free-modify', params: { articleNo: article.value.articleNo } });
}

</script>

<template>
    <div class="container mt-5 shadow p-4 rounded">
        <h4 class="fw-bolder text-center">자유게시판</h4>
        <div class="container mt-5">
            <div class="row justify-content-center">
                <table class="table">
                    <thead>
                        <tr>
                            <th colspan="9" class="table-dark text-center">{{ article.subject }}</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <th colspan="1" class="table-secondary text-center">작성자 </th>
                            <th colspan="2" class="text-center">{{ article.userId }}</th>
                            <th colspan="1" class="table-secondary text-center">작성일자 </th>
                            <th colspan="2" class="text-center">{{ article.registerTime }}</th>
                            <th colspan="1" class="table-secondary text-center">조회수 </th>
                            <th colspan="2" class="text-center">{{ article.hit }}</th>
                        </tr>
                        <tr>
                            <td colspan="9" v-html="article.content"></td>
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
                <button class="btn btn-primary btn-sm" @click="moveToModify">글 수정</button>
                <button class="btn btn-danger ms-3 btn-sm" @click="deleteFreeArticle">글 삭제</button>
            </div>
        </div>
    </div>


</template>

<style scoped></style>