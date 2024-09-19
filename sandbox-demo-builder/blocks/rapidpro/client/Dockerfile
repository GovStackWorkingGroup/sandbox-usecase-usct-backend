FROM python:3.9

ADD connect.py .

RUN pip install minio

# Set environment variables
ENV MINIO_HOST=rapidpro-minio
ENV MINIO_ACCESS_KEY=admin
ENV MINIO_SECRET_KEY=admin123

CMD ["python", "./connect.py"]