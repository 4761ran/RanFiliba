
import pandas as pd
from sklearn.tree import DecisionTreeClassifier

# שלב 1: הגדרת נתונים
data = {
    'צבע': [0, 1, 0, 1],     # 0 = אדום, 1 = צהוב
    'גודל': [3, 6, 4, 7],     # ערכים מספריים
    'פרי': ['תפוח', 'בננה', 'תפוח', 'בננה']
}

#יצירת טבלה חכמה
df = pd.DataFrame(data)

#y זה
# שלב 2: הפרדה בין מאפיינים (X) לתוויות (y)
X = df[['צבע', 'גודל']]
y = df['פרי']

# שלב 3: אימון המודל
model = DecisionTreeClassifier()
model.fit(X, y)

# שלב 4: בדיקת ניבוי על פריט חדש
# צבע=אדום (0), גודל=4
result = model.predict([[0, 4]])
print("המודל חושב שזה:", result[0])


# THATS RAN FILIBA
