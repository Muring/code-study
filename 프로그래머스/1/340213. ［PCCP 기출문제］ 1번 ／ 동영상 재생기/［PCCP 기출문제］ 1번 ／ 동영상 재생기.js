function solution(video_len, pos, op_start, op_end, commands) {
    // "mm:ss"를 초 단위로 변환하는 함수
    function timeToSeconds(time) {
        const [minutes, seconds] = time.split(':').map(Number);
        return minutes * 60 + seconds;
    }

    // 초 단위를 "mm:ss" 형식으로 변환하는 함수
    function secondsToTime(seconds) {
        const minutes = Math.floor(seconds / 60);
        const secs = seconds % 60;
        return `${String(minutes).padStart(2, '0')}:${String(secs).padStart(2, '0')}`;
    }

    // 변수들을 초 단위로 변환
    let currentTime = timeToSeconds(pos);
    const videoLength = timeToSeconds(video_len);
    const openingStart = timeToSeconds(op_start);
    const openingEnd = timeToSeconds(op_end);

    // 명령 처리
    for (let command of commands) {
        // 오프닝 구간일 경우 오프닝 끝나는 위치로 이동
        if (openingStart <= currentTime && currentTime <= openingEnd) {
            currentTime = openingEnd;
        }
        
        if (command === 'prev') {
            // 10초 전으로 이동
            currentTime = Math.max(0, currentTime - 10);
        } else if (command === 'next') {
            // 10초 후로 이동
            currentTime = Math.min(videoLength, currentTime + 10);
        }
    }
    
    if (openingStart <= currentTime && currentTime <= openingEnd) {
        currentTime = openingEnd;
    }

    // 결과 반환
    return secondsToTime(currentTime);
}