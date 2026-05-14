from pypdf import PdfReader
reader = PdfReader('sahyadri_samrakshane_prd.pdf')
text = '\n'.join(page.extract_text() for page in reader.pages)
with open('prd_text.txt', 'w', encoding='utf-8') as f:
    f.write(text)
