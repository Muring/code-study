function solution(today, terms, privacies) {
    const answer = [];
    
    // 오늘 날짜 및 개인정보 수집 일자를 모두 일자수로 변환
    const toTotalDays = (dateStr) => {
        const [year, month, day] = dateStr.split('.').map(Number);
        return year * 12 * 28 + month * 28 + day;
    };
    
    // 약관을 확인하기 위한 매핑
    const termMap = [];
    for(const term of terms) {
        const [type, duration] = term.split(' ');
        termMap[type] = Number(duration);
    }
    
    // 매핑한 후, 각 약관에 따른 유효기간을 더한 값이 오늘 날짜 값보다 작으면 파기해야 할 개인정보
    const todaysTotal = toTotalDays(today);
    for(let idx = 0; idx < privacies.length; idx++) {
        var [date, privacy] = privacies[idx].split(' ');
        const privacyStartDateDays = toTotalDays(date);
        const duration = termMap[privacy];
        
        const expirationDateDays = privacyStartDateDays +( duration * 28) - 1;
        if(expirationDateDays < todaysTotal) {
            answer.push(idx + 1);
        }
        
    }
    
    return answer;
}