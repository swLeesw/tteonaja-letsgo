import { localAxios } from "@/util/http-commons";

const local = localAxios();

// 사용자 로그인
async function userConfirm(param, success, fail) {
  await local.post(`member/login`, param).then(success).catch(fail);
}

// 사용자 정보 조회
async function findById(userId, success, fail) {
  local.defaults.headers["Authorization"] =
    sessionStorage.getItem("accessToken");
  await local.get(`member/info/${userId}`).then(success).catch(fail);
}

// access token 재생성
async function tokenRegeneration(user, success, fail) {
  local.defaults.headers["refreshToken"] =
    sessionStorage.getItem("refreshToken");
  console.log(sessionStorage.getItem("refreshToken"))
  console.log(user);
  await local.post(`member/refresh`, user).then(success).catch(fail);
}

async function logout(userId, success, fail) {
  await local.get(`member/logout/${userId}`).then(success).catch(fail);
}

async function register(newUser, success, fail) {
  await local.post(`member/join`, newUser).then(success).catch(fail);
}

async function idCheck(userId, success, fail) {
  await local.get(`member/idcheck/${userId}`).then(success).catch(fail);
}

async function modifyUser(newUser, success, fail) {
  await local.put(`member`, newUser).then(success).catch(fail);
}

async function deleteUser(userId, success, fail) {
  await local.delete(`member/${userId}`).then(success).catch(fail);
}

async function checkPass(params, success, fail) {
  await local.post(`member/passcheck`, params).then(success).catch(fail);
}
export { userConfirm, findById, tokenRegeneration, logout, idCheck, register, modifyUser, deleteUser, checkPass };
