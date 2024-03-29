import logging


class CustomLogger:
    """
    Python의 내장 로깅 모듈을 사용하여 로그 메시지를 출력
    """
    def __init__(self, level=logging.INFO):
        self.logger = logging.getLogger(__name__)
        self.logger.setLevel(level)
        formatter = logging.Formatter('%(asctime)s - %(name)s - %(levelname)s - %(message)s')

        ch = logging.StreamHandler()
        ch.setLevel(level)
        ch.setFormatter(formatter)
        self.logger.addHandler(ch)

    def info(self, message):
        self.logger.info(message)

    def warning(self, message):
        self.logger.warning(message)

    def error(self, message):
        self.logger.error(message)

    def exception(self, message):
        self.logger.exception(message)
