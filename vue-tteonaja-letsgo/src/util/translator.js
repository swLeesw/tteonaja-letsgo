
import OpenAI from 'openai';
const { VITE_OPENAI_API_KEY } = import.meta.env;

const getGPTResponse = async (article) => {
    if (!article || article.length === 0) {
        console.log('No articles to process');
        return;
    }

    try {
        const openai = new OpenAI({
            apiKey: VITE_OPENAI_API_KEY,
            dangerouslyAllowBrowser: true,
        });
        
        console.log(article);
        // const prompt = `Translate the following article to Korean: ${article.content}`;
        const prompt = article
        const response = await openai.chat.completions.create({
            messages: [
                {
                    role: "system",
                    content: "You will be provided with a text with an html tag. And your task is to remove the html tag and translate the remaining Korean sentences into English."
                },
                {
                    role: 'user',
                    content: prompt,
                },
            ],
            model: 'gpt-3.5-turbo',
        });
        console.log('chatGPT 결과: ', response.choices[0].message.content);
        return response.choices[0].message.content

    } catch (error) {
        console.log(error);
        console.log('chatGPT: 🚨 에러가 발생했습니다.');
        return;
    }
};

export {
    getGPTResponse
};










// 반복문

// const getGPTResponse = async (articles) => {
//     if (!articles || articles.length === 0) {
//         console.log('No articles to process');
//         return;
//     }

//     try {
//         const openai = new OpenAI({
//             apiKey: VITE_OPENAI_API_KEY,
//             dangerouslyAllowBrowser: true,
//         });
        
//         for (const article of articles) {
//             console.log(article);
//             // const prompt = `Translate the following article to Korean: ${article.content}`;
//             const prompt = `${article.content}`
//             const response = await openai.chat.completions.create({
//                 messages: [
//                     {
//                         role: "system",
//                         content: "You will be provided with a sentence in Korean, and your task is to translate it into English."
//                     },
//                     {
//                         role: 'user',
//                         content: prompt,
//                     },
//                 ],
//                 model: 'gpt-3.5-turbo',
//             });
//             translated.value.push(response.choices[0].message.content)
//             console.log('chatGPT 결과: ', response.choices[0].message.content);
//         }

//     } catch (error) {
//         console.log(error);
//         console.log('chatGPT: 🚨 에러가 발생했습니다.');
//     }
// };