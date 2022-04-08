import sys
import io
import jieba
import jieba.analyse
import jieba.posseg as posg


def extractNoun(sentence):
    # 使用 jieba 进行词性切分，allowPOS 指定允许的词性，这里选择名词 n 和地名 ns
    jieba.load_userdict("C:\\Users\\cdx\\code\\UMLGenerator\\NounExtract\\uml_dict.txt")

    # sentence = "添加一个名为天气的类，实现了主题，有一个名为气温的类型为小数的私有成员变量。"
    taglist = ['create', 'vvv', 'aaa', 'nnn', 'n', 'type', 'eng', 'name', 't', 'v', 'mv', 'pattern']

    # print(sentence)
    for sent in sentence.split('，'):
        kw = [i for i in posg.cut(sent) if i.flag in taglist]
        print(' '.join([(x.word) for x in kw]))


if __name__ == '__main__':
    sys.stdout = io.TextIOWrapper(sys.stdout.buffer, encoding='utf-8')
    extractNoun(sys.argv[1])
    # print(sys.argv[1])