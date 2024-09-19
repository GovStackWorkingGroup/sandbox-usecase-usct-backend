import os
import logging
from minio import Minio
logging.basicConfig(level=logging.INFO)

# Read environment variables
minio_host = os.getenv('MINIO_HOST', 'localhost:9000')  # Default to 'localhost:9000' if not set
minio_access_key = os.getenv('MINIO_ACCESS_KEY', 'admin')
minio_secret_key = os.getenv('MINIO_SECRET_KEY', 'admin123')

client = Minio(
    minio_host,
    minio_access_key,
    minio_secret_key,
    secure=False
)

buckets = client.list_buckets()
logging.info("print buckets names ...")

for bucket in buckets:
    print(bucket.name)
    logging.info(bucket.name)
