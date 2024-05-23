import { localAxios } from "@/util/http-commons";

const local = localAxios();

async function listArticle(params, success, fail) {
  local.get(`/board/${params.boardType}/list`, { params: params }).then(success).catch(fail);
}

async function getArticle(params, success, fail) {
  await local.get(`/board/${params.boardType}/${params.articleNo}`).then(success).catch(fail);
}

async function getCourse(params, success, fail) {
  await local.get(`/board/${params.boardType}/course/${params.articleNo}`).then(success).catch(fail);
}

async function registArticle(params, success, fail) {
  await local.post(`/board/${params.boardType}/regist`, JSON.stringify(params.article)).then(success).catch(fail);
}

async function registCourse(params, success, fail) {
  await local.post(`/board/${params.boardType}/registList`).then(success).catch(fail);
}

async function modifyArticle(params, success, fail) {
  await local.put(`/board/${params.boardType}`, JSON.stringify(params.article)).then(success).catch(fail);
}

async function deleteArticle(params, success, fail) {
  await local.delete(`/board/${params.boardType}/${params.articleNo}`).then(success).catch(fail);
}

async function likeArticle(params, success, fail) {
  await local.get(`/board/${params.boardType}/like/${params.articleNo}/${params.userId}`).then(success).catch(fail);
}

async function registComment(params, success, fail) {
  console.log(params.comment);
  await local
    .post(`board/${params.boardType}-comment/regist`, JSON.stringify(params.comment))
    .then(success)
    .catch(fail);
}

async function listComment(params, success, fail) {
  await local.get(`board/${params.boardType}-comment/list/${params.articleNo}`).then(success).catch(fail);
}

async function deleteComment(params, success, fail) {
  await local.delete(`board/${params.boardType}-comment/${params.commentNo}`).then(success).catch(fail);
}

async function getTop(params, success, fail) {
  await local.get(`board/${params.boardType}/top`).then(success).catch(fail);
}

export {
  listArticle,
  getArticle,
  getCourse,
  registArticle,
  registCourse,
  modifyArticle,
  deleteArticle,
  likeArticle,
  registComment,
  listComment,
  deleteComment,
  getTop
};
