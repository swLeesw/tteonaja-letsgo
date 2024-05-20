<script setup>
import { QuillEditor } from '@vueup/vue-quill';
import '@vueup/vue-quill/dist/vue-quill.snow.css';

import BoardCommentFormItem from './item/BoardCommentFormItem.vue';

const props = defineProps({
    commentParams: Object
})

</script>

<template>
    <BoardCommentFormItem :comment-params="props.commentParams" type="regist"/>
</template>

<style scoped>

</style>