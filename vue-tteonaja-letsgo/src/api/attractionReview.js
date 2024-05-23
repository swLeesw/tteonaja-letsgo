import { localAxios } from "@/util/http-commons";

const local = localAxios();

function listReview(param, success, fail) {
  local.get(`/review/list/${param}`, param).then(success).catch(fail);
}

//check: false => likeReview, check: true => deleteReview
function likeReview(reviewNo, userId, success, fail) {
  local.get(`/review/like/${reviewNo}/${userId}`, reviewNo, userId).then(success).catch(fail);
}

function registReview(param, success, fail) {
  local.post(`/review/regist`, param).then(success).catch(fail);
}

function deleteReview(param, success, fail) {
  local.delete(`/review/${param}`, param).then(success).catch(fail);
}

export { listReview, likeReview, registReview, deleteReview };
