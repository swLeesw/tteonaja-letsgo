<script setup>
import { onMounted, ref } from 'vue';
import { useRoute, useRouter } from 'vue-router';
import { deleteArticle, getArticle, likeArticle } from '@/api/board';
import BoardCommentWrite from '@/components/board/comment/BoardCommentWrite.vue';
import Swal from 'sweetalert2';
import BoardCommentList from '../comment/BoardCommentList.vue';
import { jwtDecode } from 'jwt-decode';
import { getGPTResponse } from '@/util/translator';
import { getAttractionById } from '@/api/attractionInfo';
// import { Typed } from "@duskmoon/vue3-typed-js";
// import { TypedOptions } from "@duskmoon/vue3-typed-js";

const route = useRoute();
const router = useRouter();

const params = ref(
    {
        boardType: 'travel',
        articleNo: route.params.id
    }
);

const currentUserId = ref("");

const article = ref({});
const courseList = ref();
const attractionList = ref([]);

const getTravelArticleDetail = async () => {
    await getArticle(params.value,
        (response) => {
            article.value = response.data;
            console.log(response);
        },
        (error) => {
            console.log("게시물 상세 정보 오류");
            console.log(error);
        }
    );

    courseList.value = JSON.parse(article.value.travelList);

    for (const course of courseList.value) {
        await getAttractionById(
            course.id,
            (response) => {
                console.log(response);
                attractionList.value.push(response.data);
            },
            (error) => {
                console.log(error);
            }
        );
    }
};

const removeArticle = () => {
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
            console.log(error);
        }
    );
};

const translated = ref("");
const translateMode = ref(false);

const translate = async () => {
    if (!translateMode.value) {
        translated.value = await getGPTResponse(article.value.content);
    }
    translateMode.value = !translateMode.value;
};

// const options: TypedOptions = {
//     strings: ["Hello", "World", "This is vue3-typed-js"],
//     loop: true,
//     typeSpeed: 30,
// };

onMounted(() => {
    getTravelArticleDetail();
    currentUserId.value = jwtDecode(sessionStorage.getItem(['accessToken'])).userId;
});

const moveToList = () => {
    router.replace({ name: 'travel-list' });
};

const moveToModify = () => {
    router.push({ name: 'travel-modify', params: { articleNo: article.value.articleNo }, query: { courseList: JSON.stringify(attractionList.value) } });
};

const likeThisArticle = () => {
    likeArticle(
        {
            boardType: 'travel',
            articleNo: article.value.articleNo,
            userId: currentUserId.value
        },
        (response) => {
            if (response.data.check) {
                Swal.fire({
                    position: "top",
                    icon: "error",
                    title: "좋아요 취소",
                    showConfirmButton: false,
                    timer: 1500
                });
            } else {
                Swal.fire({
                    position: "top",
                    icon: "success",
                    title: "좋아요",
                    showConfirmButton: false,
                    timer: 1500
                });
            }
        },
        (error) => {
            console.log(error);
        }
    );
};
</script>

<template>
    <br><br>
    <div class="container mt-5 mb-5 shadow p-4 rounded">
        <h1 class="fw-light mb-5 text-center">여행 코스</h1>
        <div class="container mt-5">
            <div class="row justify-content-center">
                <table class="table">
                    <thead>
                        <tr>
                            <th colspan="13" class="table-dark text-center">{{ article.subject }}</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr class="align-middle">
                            <th colspan="1" class="table-secondary text-center">작성자 </th>
                            <th colspan="2" class="text-center">{{ article.userId }}</th>
                            <th colspan="1" class="table-secondary text-center">작성일자 </th>
                            <th colspan="2" class="text-center">{{ article.registerTime }}</th>
                            <th colspan="1" class="table-secondary text-center">조회수 </th>
                            <th colspan="2" class="text-center">{{ article.hit }}</th>
                            <th colspan="1" class="table-secondary text-center">좋아요 </th>
                            <th colspan="2" class="text-center">{{ article.courseLike }}</th>
                            <th colspan="1" class="table-secondary text-center"><a href="#" class="btn fw-bolder p-0"
                                    @click="translate">Translate<i class="bi bi-globe"></i></a></th>
                        </tr>
                        <tr v-if="!translateMode">
                            <td colspan="13" v-html="article.content" class="p-3"></td>
                        </tr>
                        <tr v-else>
                            <!-- <td colspan="10" class="p-3">
                                <Typed :options="options">
                                    <h1 class="typing"></h1>
                                </Typed>
                                <vue-typed-js :typeSpeed="60" :strings="['test']">
                                    <p></p>
                                </vue-typed-js>
                            </td> -->
                            <td colspan="10" class="p-3" v-html="translated"></td>
                        </tr>
                    </tbody>
                </table>
            </div>
        </div>
        <div class="container border p-3" id="comment_block">
            <p>여행 코스</p>
            <hr>
            <div class="overflow-x-auto border mb-3 ps-2">

                <!-- <div class="container-fluid border mb-5 ps-2"> -->
                <div class="d-inline-flex" v-for="(info, index) in attractionList" :key="index">
                    <div class="d-inline-flex ms-3 me-3 mt-4 mb-4 mycard p-3" @click="onClickMapMarker(info)">
                        <img :src="info.firstImage" style="border-radius: 10px;" alt="사진" width="120px"
                            v-show="info.firstImage != ''">
                        <img src="@/assets/logo.png" alt="사진" width="120px" v-show="info.firstImage == ''">
                        <span class="ms-3 border border-1 opacity-90"></span>
                        <div class="card-content ms-3">
                            <h5 class="card-title mb-1">{{ info.name }}</h5>
                            <p class="card-description">{{ info.addr1 }}</p>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="container border p-3 mt-3" id="comment_block">
            <p>댓글 목록</p>
            <hr>
            <BoardCommentList board-type="travel" :comment-params="params" :write-checker="writeChecker"
                @deleted-comment="deletedComment" @complete-write="completeWrite" />
            <BoardCommentWrite :comment-params="params" @write-comment="writeComment" />
        </div>

        <div
            class="container mt-3 d-flex justify-content-between align-items-center p-1">
            <div>
                <button class="btn btn-secondary btn-sm" @click="moveToList">글 목록</button>
            </div>
            <button style="margin-left: 90px;" class="btn btn-outline-danger"
                @click="likeThisArticle(route.params.id)">♥</button>
            <div :style="{ visibility: currentUserId === article.userId ? 'visible' : 'hidden' }">
                <button class="btn btn-primary btn-sm" @click="moveToModify">글 수정</button>
                <button class="btn btn-danger ms-3 btn-sm" @click="removeArticle">글 삭제</button>
            </div>
        </div>
    </div>

</template>

<style scoped>
.overflow-x-auto {
    white-space: nowrap;
}

.mycard:hover {
    transform: scale(1.05);
    background-color: rgba(67, 180, 255, 0.158);
    border-radius: 10px;
    display: inline-block;
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
</style>