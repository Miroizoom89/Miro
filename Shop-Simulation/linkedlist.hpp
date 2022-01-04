#include <iostream>
#pragma once

using namespace std;

template <class T>
class LinkedList
{
private:
    struct Node
    {
        T data;
        Node * next;
        Node(): next(nullptr){}
        Node(T val): data(val), next(nullptr){}
    };
    Node *head;

    void delete_first()
    {
        Node *temp=head;
        head=head->next;
        delete temp;
    };

    T get_first()
    {
        Node *temp=head;
        return temp->data;
    };

    void display(ostream& out = cout) const
    {
        Node *node = head;
        while(node != nullptr)
        {
            out << node->data << " ";
            node = node->next;
        }
    }

 public:

     LinkedList() : head(nullptr){}

    ~LinkedList()
    {
        Node *tmp = nullptr;
        while (head)
        {
            tmp = head;
            head = head->next;
            delete tmp;
        }
        head =nullptr;
    };

    void insert_end(T data)
    {
        Node *t = new Node(data);
        Node *tmp = head;
        if (tmp == nullptr)
        {
            head = t;
        }
        else
        {
            while (tmp->next != nullptr)
            {
                tmp = tmp->next;
            }
            tmp->next = t;
        }
    };

    void insert_start(T dataa)
    {
        Node *tmp=new Node(dataa);
        tmp->next=head;
        head=tmp;
    };

    T get_position(int pos)
    {
        if(pos>=0 && pos<(this->get_size()))
        {
            if(pos==0)
            {
                return this->get_first();
            }
            else
            {
                Node *current=head;
                for(int i=0;i<pos;i++)
                {
                    current=current->next;
                }
                return current->data;
            }
        }
        else
        {
            throw "Podano nieprawidlowa pozycje \n";
        }
    };

    T get_next_position(int pos)
    {
        if(pos>=0 && pos<(this->get_size()-1))
        {
            return this->get_position(pos+1);
        }
        else
        {
            throw "Podano nieprawidlowa pozycje \n";
        }
    };

    void delete_position(int pos)
    {
        if(pos>0 && pos<(this->get_size()))
        {
            Node *current=head;
            Node *previous=nullptr;
            for(int i=0;i<pos;i++)
            {
                previous=current;
                current=current->next;
            }
            previous->next=current->next;
            delete current;
        }
        else if(pos==0)
        {
            this->delete_first();
        }
        else
        {
            cout<<"Podano nieprawidlowa pozycje \n";
        }
    };

    int get_size()
    {
        if(head!=nullptr)
        {
            int i=1;
            Node *current=head;
            while(current->next!=nullptr)
            {
                current=current->next;
                i++;
            }
            return i;
        }
        else
        {
            return 0;
        }
    };

    bool check_if_empty()
    {
        return get_size()==0;
    };

    friend ostream & operator<<(ostream & os, const LinkedList<T> & ll)
    {
        ll.display(os);
        return os;
    };

    bool operator== (LinkedList<T> &l2)
    {
        if(this->get_size()==l2.get_size()){
            for(int i=0;i<l2.get_size();i++)
            {
                if(this->get_position(i)!=l2.get_position(i))
                {
                    return false;
                }
            }
            return true;
        }
        else
        {
            return false;
        }
    };
};
