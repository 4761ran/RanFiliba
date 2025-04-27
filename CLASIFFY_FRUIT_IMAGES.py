# --- שלב 1: טעינת ספריות ---
import os
import numpy as np
import matplotlib.pyplot as plt
import tensorflow as tf
from keras.src.legacy.preprocessing.image import ImageDataGenerator
from sklearn.metrics import classification_report, confusion_matrix

# --- שלב 2: הגדרת נתיב הנתונים ---
data_dir = r'C:\Users\ehudf\Downloads\archive.zip\fruits-360_100x100\fruits-360'  # שנה את הנתיב למקום בו שמרת את התמונות
img_height, img_width = 100, 100
batch_size = 32

# --- שלב 3: עיבוד וטעינת הנתונים ---
train_datagen = ImageDataGenerator(
    rescale=1. / 255,
    validation_split=0.2  # חלק ל-80% אימון ו-20% בדיקה
)

train_generator = train_datagen.flow_from_directory(
    data_dir,
    target_size=(img_height, img_width),
    batch_size=batch_size,
    class_mode='categorical',
    subset='training'
)

validation_generator = train_datagen.flow_from_directory(
    data_dir,
    target_size=(img_height, img_width),
    batch_size=batch_size,
    class_mode='categorical',
    subset='validation'
)

# --- שלב 4: בניית המודל (CNN בסיסי) ---
model = tf.keras.Sequential([
    tf.keras.layers.Conv2D(32, (3, 3), activation='relu', input_shape=(img_height, img_width, 3)),
    tf.keras.layers.MaxPooling2D(2, 2),

    tf.keras.layers.Conv2D(64, (3, 3), activation='relu'),
    tf.keras.layers.MaxPooling2D(2, 2),

    tf.keras.layers.Flatten(),
    tf.keras.layers.Dense(128, activation='relu'),
    tf.keras.layers.Dense(train_generator.num_classes, activation='softmax')
])

# קומפילציה
model.compile(optimizer='adam',
              loss='categorical_crossentropy',
              metrics=['accuracy'])

# --- שלב 5: אימון המודל ---
history = model.fit(
    train_generator,
    epochs=10,
    validation_data=validation_generator
)

# --- שלב 6: הערכת ביצועים ---
# דיוק ואובדן לאורך האפוקים
plt.plot(history.history['accuracy'], label='Training Accuracy')
plt.plot(history.history['val_accuracy'], label='Validation Accuracy')
plt.legend()
plt.show()

# ניבוי על סט הבדיקה
validation_generator.reset()
predictions = model.predict(validation_generator)
y_pred = np.argmax(predictions, axis=1)
y_true = validation_generator.classes

# דו"ח ביצועים
print(classification_report(y_true, y_pred, target_names=list(validation_generator.class_indices.keys())))

# מטריצת בלבול
cm = confusion_matrix(y_true, y_pred)
print(cm)


# THATS RAN FILIBA
