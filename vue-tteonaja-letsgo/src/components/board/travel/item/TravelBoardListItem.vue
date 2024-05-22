<script setup>
import { listComment } from '@/api/board';
import { onMounted, ref } from 'vue';
import { useRouter } from 'vue-router';
const props = defineProps({
    elem: Object
})

const router = useRouter()

const commentNum = ref(0)

const getCommentsList = () => {
    // console.log(elem.commentParams.boardType);
    listComment(
        {
            articleNo: props.elem.articleNo,
            boardType: 'travel'
        },
        (response) => {
            commentNum.value = response.data.length;
        },
        (error) => {
            console.log(error);
        }
    );
};

const thumbnail = ref("")

const moveToCourseDetail = () => {
    router.push({name: 'travel-detail', params: {id}})
}

onMounted(() => {
    // console.log(JSON.parse(props.elem.travelList)[0].firstImage)
    thumbnail.value = JSON.parse(props.elem.travelList)[0].firstImage
    getCommentsList()
    // console.log("후:" + travelList.value[0]);
})
</script>

<template>
    <div class="col">
        <div class="card shadow-sm">
            <!-- <svg class="bd-placeholder-img card-img-top" width="100%" height="225" xmlns="http://www.w3.org/2000/svg"
                role="img" aria-label="Placeholder: Thumbnail" preserveAspectRatio="xMidYMid slice" focusable="false">
                <title>Placeholder</title>
                <rect width="100%" height="100%" fill="#55595c" /><text x="50%" y="50%" fill="#eceeef"
                    dy=".3em">Thumbnail</text>
            </svg> -->
            <img v-show="thumbnail != ''" :src="thumbnail" class="bd-placeholder-img card-img-top" width="
                100%" height="225" alt="">
            <img v-show="thumbnail == ''" src="@/assets/attractionAlter.png" class="bd-placeholder-img rounded-circle"
                width="100" height="100" alt="">
            <div class="card-body">
                <h4 class="card-title text-center">{{ elem.subject }}</h4>
                <small class="card-subtitle text-body-secondary centered-small">{{ elem.userId }}</small>
                <hr>
                <!-- <div class="d-flex justify-content-between align-items-center"> -->
                <div class="container align-items-center">
                    <div class="text-center">
                        <RouterLink
                            :to="{name:'travel-detail', params:{id: props.elem.articleNo, commentNum: commentNum}}"
                            class="btn btn-sm btn-secondary">코스
                            보기</RouterLink>
                        <!-- <button type="button" class="btn btn-sm btn-outline-secondary" @click=""></button> -->
                    </div>
                    <!-- <small class="text-muted">9 mins</small> -->
                </div>
            </div>
        </div>
    </div>
</template>

<style scoped>
.centered-small {
    display: block;
    margin: 0 auto;
    text-align: center;
}
</style>