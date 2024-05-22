import { localAxios } from "@/util/http-commons";

const local = localAxios();

function listArticle(params, success, fail) {
  local.get(`/board/${params.boardType}/list`, { params: params }).then(success).catch(fail);
}

function getArticle(params, success, fail) {
  local.get(`/board/${params.boardType}/${params.articleNo}`).then(success).catch(fail);
}

function getCourse(params, success, fail) {
  local.get(`/board/${params.boardType}/course/${params.articleNo}`).then(success).catch(fail);
}

function registArticle(params, success, fail) {
  local.post(`/board/${params.boardType}/regist`, JSON.stringify(params.article)).then(success).catch(fail);
}

function registCourse(params, success, fail) {
  local.post(`/board/${params.boardType}/registList`).then(success).catch(fail);
}

function modifyArticle(params, success, fail) {
  local.put(`/board/${params.boardType}`, JSON.stringify(params.article)).then(success).catch(fail);
}

function deleteArticle(params, success, fail) {
  local.delete(`/board/${params.boardType}/${params.articleNo}`).then(success).catch(fail);
}

function registComment(params, success, fail) {
  console.log(params.comment);
  local
    .post(`board/${params.boardType}-comment/regist`, JSON.stringify(params.comment))
    .then(success)
    .catch(fail);
}

function listComment(params, success, fail) {
  local.get(`board/${params.boardType}-comment/list/${params.articleNo}`).then(success).catch(fail);
}

function deleteComment(params, success, fail) {
  local.delete(`board/${params.boardType}-comment/${params.commentNo}`).then(success).catch(fail);
}

function getTop(params, success, fail) {
  local.get(`board/${params.boardType}/top`).then(success).catch(fail);
}

export {
  listArticle,
  getArticle,
  getCourse,
  registArticle,
  registCourse,
  modifyArticle,
  deleteArticle,
  registComment,
  listComment,
  deleteComment,
  getTop
};
