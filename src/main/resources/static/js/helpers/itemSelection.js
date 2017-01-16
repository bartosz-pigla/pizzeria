function initializeItemsSelection(self, itemsCount, pageSize, collectionGetter, $http){
    self.selectedIdx=-1;
    self.pageSize=pageSize;
    self.pageCount=itemsCount/pageSize;

    console.log('initializing item selection');
    console.log(self.selectedIdx);

    var getCollection = function (pageNumber) {
        self.pageNumber=pageNumber;
        self.collection=collectionGetter($http, self);
        console.log(self.collection);
    };

    self.getCollection=getCollection;

    self.openItem= function (itemIndex) {
        self.selectedIdx=itemIndex;
        self.selectedItem=self.collection[self.selectedIdx];
    };

    getCollection(1);

    var pages = [];
    for (var i = 0; i < self.pageCount; i++) {
        console.log(pages);
        pages.push(i + 1);
    }

    self.pages=pages;
}