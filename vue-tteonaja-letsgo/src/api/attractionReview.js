import { localAxios } from "@/util/http-commons";

const local = localAxios();

function listReview(param, success, fail) {
    local
        .get(`/review/list/${param}`, param)
        .then(success)
        .catch(fail);
}


export {
    listReview,
}