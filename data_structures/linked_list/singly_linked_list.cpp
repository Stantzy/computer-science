class SinglyLinkedList {
    struct Node {
        int value;
        Node *next;
        Node(int val) : value(val), next(0) {}
    };
    Node *head;
    Node *tail;
    int size;
public:
    SinglyLinkedList()
        : head(0), tail(0), size(0) {}
    SinglyLinkedList(int value) : size(1)
        { head = new Node(value); tail = head; }
    SinglyLinkedList(const SinglyLinkedList&) = delete;


    ~SinglyLinkedList();

    SinglyLinkedList& operator=(const SinglyLinkedList&) = delete;
    int Search(int value) const;
    void PushFront(int value);
    void PushBack(int value);
    int PopFront();
    int PopBack();
    bool InsertAt(int value, int pos);
    bool RemoveAt(int position);
    int Size() const;
};

SinglyLinkedList::~SinglyLinkedList()
{
    Node *tmp = head;
    while(tmp) {
        head = head->next;
        delete tmp;
        tmp = head;
    }
}

int SinglyLinkedList::Search(int value) const
{
    const Node *tmp;
    int position = 0;

    for(tmp = head; tmp; tmp = tmp->next) {
        if(tmp->value == value)
            return position;
        position++;
    }

    return -1;
}

void SinglyLinkedList::PushFront(int value)
{
    Node *tmp = new Node(value);
    tmp->next = head;
    head = tmp;
    size++;
}

void SinglyLinkedList::PushBack(int value)
{
    Node *tmp = new Node(value);
    if(!head) {
        head = tail = tmp;
    } else {
        tail->next = tmp;
        tail = tmp;
    }
    size++;
}

int SinglyLinkedList::PopFront()
{
    if(!head)
        return 0;

    Node *tmp = head;
    int val = tmp->value;

    head = head->next;
    if(!head)
        tail = 0;

    delete tmp;
    size--;

    return val;
}

int SinglyLinkedList::PopBack()
{
    if(!tail)
        return 0;

    int val = tail->value;

    if(head == tail) {
        delete head;
        head = tail = 0;
    } else {
        Node *tmp = head;
        while(tmp->next != tail)
            tmp = tmp->next;

        delete tail;
        tail = tmp;
        tail->next = 0;
    }

    size--;

    return val;
}

bool SinglyLinkedList::InsertAt(int value, int pos)
{
    if(pos > size || pos < 0)
        return false;

    Node *new_node = new Node(value);
    Node *tmp = head;
    Node *tmp_prev = 0;

    for(int i = 0; i < pos; i++) {
        tmp_prev = tmp;
        tmp = tmp->next;
    }

    new_node->next = tmp;
    if(tmp_prev)
        tmp_prev->next = new_node;
    else
        head = new_node;

    if(new_node->next == 0)
        tail = new_node;

    size++;

    return true;
}

bool SinglyLinkedList::RemoveAt(int position)
{
    if(position >= size || position < 0)
        return false;

    Node *tmp = head;
    Node *tmp_prev = 0;

    for(int i = 0; i < position; i++) {
        tmp_prev = tmp;
        tmp = tmp->next;
    }

    if(tmp_prev) {
        tmp_prev->next = tmp->next;
        if(!tmp->next)
            tail = tmp_prev;
    } else {
        head = tmp->next;
        if(!head)
            tail = 0;
    }

    delete tmp;
    size--;

    return true;
}

int SinglyLinkedList::Size() const
{
    return size;
}
