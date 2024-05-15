import { localAxios } from "@/util/http-commons";

const local = localAxios();

async function userConfirm(param, success, fail) {
  await local.post(``, param).then(success).catch(fail);
}

async function findById(userid, success, fail) {
  local.defaults.headers["Authorization"] =
    sessionStorage.getItem("accessToken");
  await local.post(``).then(success).catch(fail);
}

async function tokenRegeneration(user, success, fail) {
  local.defaults.headers["refreshToken"] =
    sessionStorage.getItem("refreshToken");
  await local.post(``, param).then(success).catch(fail);
}

async function logout(userid, success, fail) {
  await local.post(``).then(success).catch(fail);
}

export { userConfirm, findById, tokenRegeneration, logout}
